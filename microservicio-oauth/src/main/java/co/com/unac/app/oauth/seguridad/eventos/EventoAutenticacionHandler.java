package co.com.unac.app.oauth.seguridad.eventos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import co.com.unac.app.commons.modelo.entidad.Usuario;
import co.com.unac.app.oauth.servicio.IServicioUsuario;
import feign.FeignException;

@Component
public class EventoAutenticacionHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(EventoAutenticacionHandler.class);

	@Autowired
	private Tracer tracer;
	
	@Autowired
	IServicioUsuario servicioUsuario;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Succes login:" + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);

		Usuario usuario = servicioUsuario.findByUsername(authentication.getName());
		if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			servicioUsuario.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el login: " + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);

		try {
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			Usuario usuario = servicioUsuario.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}

			log.info("Intento actual es de:" + usuario.getIntentos());

			usuario.setIntentos(usuario.getIntentos() + 1);

			log.info("Intento despues es de:" + usuario.getIntentos());

			errors.append(" - Intentos del login:" + usuario.getIntentos());

			if (usuario.getIntentos() >= 3) {
				String errorMaxIntentos = String.format("El usuario %s deshabilitado por máximos intentos", usuario.getUsername());
				log.error(errorMaxIntentos);
				errors.append(" - " + errorMaxIntentos);
				usuario.setEnabled(false);
			}
			
			servicioUsuario.update(usuario, usuario.getId());
			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s existe", authentication.getName()));
		}
	}

}
