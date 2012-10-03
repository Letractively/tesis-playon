package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Abono;
import tesis.playon.restful.service.IAbonoService;

@Service("abonoService")
public class AbonoService implements IAbonoService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(Abono abono) {
	logger.debug("Agregando un abono");
	Session session = sessionFactory.getCurrentSession();
	session.save(abono);
    }

    @Override
    public void update(Abono abono) {
	logger.debug("Actualizando un abono");
	Session session = sessionFactory.getCurrentSession();
	session.update(abono);
    }

    @Override
    public void delete(Abono abono) {
	logger.debug("Borrando un abono");
	Session session = sessionFactory.getCurrentSession();
	session.delete(abono);
    }

    @Override
    public List<Abono> findAll() {
	List<Abono> lista = new ArrayList<Abono>();
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("FROM  Abono");
	if (!query.list().isEmpty()) {
	    for (Object obj : query.list()) {
		lista.add((Abono) obj);
	    }
	}
	return lista;
    }

}