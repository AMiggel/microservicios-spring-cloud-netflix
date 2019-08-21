package co.com.unac.app.oauth.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.unac.app.commons.modelo.entidad.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeingCliente {
	
	@GetMapping("/usuarios/search/buscar-usuario")
	public Usuario findByUsername(@RequestParam (value="username") String username);

	@PutMapping("/usuarios/{id}")
	public Usuario update (@RequestBody Usuario usuario, @PathVariable (value ="id")Long id);
}
