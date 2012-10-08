package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.Abono;
import tesis.playon.restful.service.IAbonoService;

@Service("abonoService")
@Transactional
public class AbonoService implements IAbonoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Abono abono) {
	Session session = sessionFactory.getCurrentSession();
	session.save(abono);
    }

    @Override
    public void update(Abono abono) {
	Session session = sessionFactory.getCurrentSession();
	session.update(abono);
    }

    @Override
    public void delete(Abono abono) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(abono);
    }

    @Override
    public List<Abono> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Abono> lista = new ArrayList<Abono>();
	Query query = session.createQuery("FROM  Abono");
	if (!query.list().isEmpty()) {
	    for (Object obj : query.list()) {
		lista.add((Abono) obj);
	    }
	}
	return lista;
    }
}