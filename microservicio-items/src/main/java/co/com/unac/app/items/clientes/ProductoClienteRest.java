package co.com.unac.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.unac.app.commons.modelo.entidad.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	//consume servicio del servicio producto
	@GetMapping("/productos")
	public List<Producto> listar();
	
	@GetMapping("/productos/{id}")
	public Producto detalle(@PathVariable (value="id") Long id);
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto,@PathVariable (value="id") Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable (value="id") Long id);
}
