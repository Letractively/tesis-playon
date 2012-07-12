package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IComentarioDao;
import tesis.playon.web.model.Comentario;

/**
 * 
 * @author garribere
 * 
 */
@Repository("comentarioDao")
public class ComentarioDao extends CustomHibernateDaoSupport implements IComentarioDao {

    public void save(Comentario comentario) {
	getHibernateTemplate().save(comentario);
    }

    public void update(Comentario comentario) {
	getHibernateTemplate().update(comentario);
    }

    public void delete(Comentario comentario) {
	getHibernateTemplate().delete(comentario);
    }

    // no se si este findBy es asi, hay q verlo!
//    public Comentario findByNombrePlaya(String nombrePlaya) {
//	List<?> list = getHibernateTemplate().find("from Comentario where nombre=?", nombrePlaya);
//	return (Comentario) list.get(0);
//    }
    
    public List<Comentario> findAll(){
	List<Comentario> comentarios = new ArrayList<Comentario>();
	List<?> list = getHibernateTemplate().find("from Comentario");
	for (Object object : list) {
	    comentarios.add((Comentario)object);
	}
	return comentarios;
    }

}
