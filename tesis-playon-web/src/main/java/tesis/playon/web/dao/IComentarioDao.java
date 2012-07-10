package tesis.playon.web.dao;

import tesis.playon.web.model.Comentario;

/**
 * @author garribere	
 *
 */
public interface IComentarioDao {

    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Comentario comentario);

    Comentario findByNombrePlaya(String nombrePlaya);
}
