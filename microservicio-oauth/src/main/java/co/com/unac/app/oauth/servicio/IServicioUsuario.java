package co.com.unac.app.oauth.servicio;



import co.com.unac.app.commons.modelo.entidad.Usuario;

public interface IServicioUsuario {

public Usuario findByUsername (String username);
public Usuario update (Usuario usuario, Long id);

}
