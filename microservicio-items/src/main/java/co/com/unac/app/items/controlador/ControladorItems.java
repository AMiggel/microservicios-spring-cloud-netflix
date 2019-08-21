package co.com.unac.app.items.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import co.com.unac.app.items.modelo.Item;
import co.com.unac.app.commons.modelo.entidad.Producto;
import co.com.unac.app.items.modelo.servicio.IServicioItem;

@RefreshScope
@RestController
public class ControladorItems {
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("servicioFeign")
	private IServicioItem servicioItem;
	
	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping("/productos")
	public List<Item> listar() {
		return servicioItem.findAll();
	}

	@HystrixCommand(fallbackMethod = "alternativo")
	@GetMapping("/productos/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable (value="id") Long id, @PathVariable (value="cantidad") Integer cantidad) {
		return servicioItem.findById(id, cantidad);
	}
	
	public Item alternativo(Long id, Integer cantidad) {
		Item item= new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("producto");
		producto.setPrecio(5000.00);
		item.setProducto(producto);
		return item;
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		Map<String, String> json = new HashMap<>();
		json.put("texto",texto);
		json.put("puerto",puerto);
		
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre",env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email",env.getProperty("configuracion.autor.email"));

		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto) {
		return servicioItem.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable (value="id")  Long id) {
		return servicioItem.upadte(producto, id);
	}
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable (value="id")Long id){
		servicioItem.delete(id);
	}

}
