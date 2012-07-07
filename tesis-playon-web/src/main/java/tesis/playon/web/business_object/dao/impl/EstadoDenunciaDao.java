/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.model.EstadoDenuncia;
import tesis.playon.web.business_object.dao.IEstadoDenunciaDao;

/**
 * @author Pablo
 *
 */
@Repository("estadoDenunciaDao")
public class EstadoDenunciaDao extends CustomHibernateDaoSupport implements IEstadoDenunciaDao{

    public void save(EstadoDenuncia estadoDenuncia) {
	getHibernateTemplate().save(estadoDenuncia);
    }

    public void update(EstadoDenuncia estadoDenuncia) {
	getHibernateTemplate().update(estadoDenuncia);
    }

    public void delete(EstadoDenuncia estadoDenuncia) {
	getHibernateTemplate().delete(estadoDenuncia);
    }

    public EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia) {
	List<?> list = getHibernateTemplate().find("from EstadoDenuncia where nombre=?", nombreDenuncia);
	return (EstadoDenuncia) list.get(0);
    }
}
