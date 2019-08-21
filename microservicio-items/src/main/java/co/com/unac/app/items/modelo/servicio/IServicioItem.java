package co.com.unac.app.items.modelo.servicio;

import java.util.List;

import co.com.unac.app.items.modelo.Item;
import co.com.unac.app.commons.modelo.entidad.Producto;

public interface IServicioItem {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	public Producto upadte(Producto producto, Long id);
	public void delete (Long id);
}
