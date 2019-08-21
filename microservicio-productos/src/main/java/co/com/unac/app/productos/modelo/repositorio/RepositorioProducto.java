package co.com.unac.app.productos.modelo.repositorio;

import org.springframework.data.repository.CrudRepository;

import co.com.unac.app.commons.modelo.entidad.Producto;

public interface RepositorioProducto extends CrudRepository<Producto, Long> {

}
