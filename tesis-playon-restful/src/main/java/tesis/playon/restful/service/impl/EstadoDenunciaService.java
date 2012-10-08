package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.EstadoDenuncia;
import tesis.playon.restful.service.IEstadoDenunciaService;

@Service("estadoDenunciaService")
@Transactional
public class EstadoDenunciaService implements IEstadoDenunciaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(EstadoDenuncia estadoDenuncia) {
	Session session = sessionFactory.getCurrentSession();
	session.save(estadoDenuncia);
    }

    @Override
    public void update(EstadoDenuncia estadoDenuncia) {
	Session session = sessionFactory.getCurrentSession();
	session.update(estadoDenuncia);
    }

    @Override
    public void delete(EstadoDenuncia estadoDenuncia) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(estadoDenuncia);
    }

    @Override
    public EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from EstadoDenuncia where nombre=?")
		.setParameter(0, nombreDenuncia).list();
	return (EstadoDenuncia) list.get(0);
    }

    @Override
    public List<EstadoDenuncia> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<EstadoDenuncia> estados = new ArrayList<EstadoDenuncia>();
	List<?> list = session.createQuery("from EstadoDenuncia").list();
	for (Object object : list) {
	    estados.add((EstadoDenuncia) object);
	}
	return estados;
    }
}