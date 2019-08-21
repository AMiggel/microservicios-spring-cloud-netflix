package co.com.unac.app.usuarios.modelo.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import co.com.unac.app.commons.modelo.entidad.Usuario;

@RepositoryRestResource(path= "usuarios")
public interface IRepositorioUsuario extends PagingAndSortingRepository<Usuario, Long> {

	@RestResource(path="buscar-usuario")
	public Usuario findByUsername(@Param("username") String username);
}
