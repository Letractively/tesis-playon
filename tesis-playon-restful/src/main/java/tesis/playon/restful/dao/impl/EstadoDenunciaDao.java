package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IEstadoDenunciaDao;
import tesis.playon.restful.domain.EstadoDenuncia;

@Repository("estadoDanenciaDao")
public class EstadoDenunciaDao extends HibernateDaoSupport implements IEstadoDenunciaDao {

    @Override
    public void save(EstadoDenuncia estadoDenuncia) {
	getSessionFactory().getCurrentSession().save(estadoDenuncia);
    }

    @Override
    public void update(EstadoDenuncia estadoDenuncia) {
	getSessionFactory().getCurrentSession().update(estadoDenuncia);
    }

    @Override
    public void delete(EstadoDenuncia estadoDenuncia) {
	getSessionFactory().getCurrentSession().delete(estadoDenuncia);
    }

    @Override
    public EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoDenuncia where nombre=?")
		.setParameter(0, nombreDenuncia).list();
	return (EstadoDenuncia) list.get(0);
    }

    @Override
    public List<EstadoDenuncia> findAll() {
	List<EstadoDenuncia> estados = new ArrayList<EstadoDenuncia>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from EstadoDenuncia").list();
	for (Object object : list) {
	    estados.add((EstadoDenuncia) object);
	}
	return estados;
    }

}