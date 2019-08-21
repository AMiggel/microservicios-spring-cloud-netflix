package co.com.unac.app.productos.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.unac.app.productos.modelo.Servicio.IServicioProducto;
import co.com.unac.app.commons.modelo.entidad.Producto;

@RestController
public class controladorProducto {

	
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
	private IServicioProducto servicioProducto;

	@GetMapping("/productos")
	public List<Producto> mostrarProductos() {
		return servicioProducto.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/productos/{id}")
	public Producto detalleProducto(@PathVariable Long id) throws Exception {
		Producto producto = servicioProducto.findById(id);
		producto.setPort(port);
		
		/*try {
		Thread.sleep(2000L);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}*/
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return servicioProducto.save(producto);
		
	}
	
	@PutMapping("/editar/{id}")
	public Producto editar (@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb= servicioProducto.findById(id);
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return servicioProducto.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id ) {
		servicioProducto.deleteById(id);
	}
	
}
