package co.com.unac.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import co.com.unac.app.commons.modelo.entidad.Rol;
import co.com.unac.app.commons.modelo.entidad.Usuario;

@Configuration
public class ConfiguracionRepositorio implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

	
}
