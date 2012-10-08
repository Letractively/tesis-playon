package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Cliente;
import tesis.playon.restful.domain.Favorito;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IFavoritoService;

@Service("favoritoService")
public class FavoritoService implements IFavoritoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Favorito favorito) {
	Session session = sessionFactory.getCurrentSession();
	session.save(favorito);
    }

    @Override
    public void update(Favorito favorito) {
	Session session = sessionFactory.getCurrentSession();
	session.update(favorito);
    }

    @Override
    public void delete(Favorito favorito) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(favorito);
    }

    @Override
    public List<Favorito> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = session.createQuery("from Favorito").list();
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
	Session session = sessionFactory.getCurrentSession();
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = session.createQuery("from Favorito where cliente=?")
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
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Favorito where cliente=? and playa=?")
		.setParameter(0, cliente).setParameter(1, playa).list();
	if (list.isEmpty()) {
	    return false;
	}
	return true;
    }
}