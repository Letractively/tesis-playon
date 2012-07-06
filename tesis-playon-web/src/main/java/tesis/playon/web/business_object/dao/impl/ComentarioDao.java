package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IComentarioDao;
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
    public Comentario findByNombrePlaya(String nombrePlaya) {
	List<?> list = getHibernateTemplate().find("from Comentario where nombre=?", nombrePlaya);
	return (Comentario) list.get(0);
    }

}
