package co.com.unac.app.items.modelo.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.unac.app.items.clientes.ProductoClienteRest;
import co.com.unac.app.items.modelo.Item;
import co.com.unac.app.commons.modelo.entidad.Producto;

@Service("servicioFeign")
public class ServicioItemFeign implements IServicioItem {

	@Autowired
	private ProductoClienteRest productoFeing;

	@Override
	public List<Item> findAll() {

		return productoFeing.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoFeing.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {

		return productoFeing.crear(producto);
	}

	@Override
	public Producto upadte(Producto producto, Long id) {
		return productoFeing.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		productoFeing.eliminar(id);
	}

}
