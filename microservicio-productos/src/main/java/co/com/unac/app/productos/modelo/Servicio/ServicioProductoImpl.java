package co.com.unac.app.productos.modelo.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.unac.app.commons.modelo.entidad.Producto;
import co.com.unac.app.productos.modelo.repositorio.RepositorioProducto;

@Service
public class ServicioProductoImpl implements IServicioProducto {

	@Autowired
	private RepositorioProducto repositorioProducto;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) repositorioProducto.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return repositorioProducto.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return repositorioProducto.save(producto);
	}
	@Override
	@Transactional
	public void deleteById(Long id) {
		repositorioProducto.deleteById(id);
		
	}

}
