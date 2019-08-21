package co.com.unac.app.productos.modelo.Servicio;

import java.util.List;

import co.com.unac.app.commons.modelo.entidad.Producto;

public interface IServicioProducto {

	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	public void deleteById(Long id);
}
