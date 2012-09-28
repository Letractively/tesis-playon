package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IFavoritoDao;
import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Favorito;
import tesis.playon.restful.domain.Playa;

@Repository("favoritoDao")
public class FavoritoDao extends HibernateDaoSupport implements IFavoritoDao {

    @Override
    public void save(Favorito favorito) {
	getSessionFactory().getCurrentSession().save(favorito);
    }

    @Override
    public void update(Favorito favorito) {
	getSessionFactory().getCurrentSession().update(favorito);
    }

    @Override
    public void delete(Favorito favorito) {
	getSessionFactory().getCurrentSession().delete(favorito);
    }

    @Override
    public List<Favorito> findAll() {
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		favoritos.add((Favorito) object);
	    }
	    return favoritos;
	}
	return null;
    }

    @Override
    public List<Favorito> findByCliente(Cliente cliente) {
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito where cliente=?")
		.setParameter(0, cliente).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		favoritos.add((Favorito) object);
	    }
	    return favoritos;
	}
	return null;
    }

    @Override
    public boolean isFavorito(Cliente cliente, Playa playa) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito where cliente=? and playa=?")
		.setParameter(0, cliente).setParameter(1, playa).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }

}