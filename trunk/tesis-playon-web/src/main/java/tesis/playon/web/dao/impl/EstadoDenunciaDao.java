package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IEstadoDenunciaDao;
import tesis.playon.web.model.EstadoDenuncia;

/**
 * @author Pablo
 * 
 */
@Repository("estadoDenunciaDao")
public class EstadoDenunciaDao extends CustomHibernateDaoSupport implements IEstadoDenunciaDao {

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

    public List<EstadoDenuncia> findAll() {
	List<EstadoDenuncia> estados = new ArrayList<EstadoDenuncia>();
	List<?> list = getHibernateTemplate().find("from EstadoDenuncia");
	for (Object object : list) {
	    estados.add((EstadoDenuncia) object);
	}
	return estados;
    }
}