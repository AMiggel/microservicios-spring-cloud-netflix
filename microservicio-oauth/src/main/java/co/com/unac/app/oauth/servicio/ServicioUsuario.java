package co.com.unac.app.oauth.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brave.Tracer;
import co.com.unac.app.commons.modelo.entidad.Usuario;
import co.com.unac.app.oauth.cliente.UsuarioFeingCliente;
import feign.FeignException;

@Service
public class ServicioUsuario implements UserDetailsService, IServicioUsuario {

	private Logger log = LoggerFactory.getLogger(ServicioUsuario.class);

	@Autowired
	private Tracer tracer;

	@Autowired
	private UsuarioFeingCliente cliente;

	// en este metodo se obtiene el usuario por nombre de usuario, para autenticarlo
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Usuario usuario = cliente.findByUsername(username);
			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> log.info("Rol" + authority.getAuthority())).collect(Collectors.toList());

			return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
					authorities);

		} catch (FeignException e) {
			String error = "Error en el login, no existe el usuario " + username + "en el sistema";
			log.error(error);

			tracer.currentSpan().tag("error.mensaje", error + ":" + e.getMessage());

			throw new UsernameNotFoundException(error);
		}

	}

	@Override
	public Usuario findByUsername(String username) {

		return cliente.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {

		return cliente.update(usuario, id);
	}

}
