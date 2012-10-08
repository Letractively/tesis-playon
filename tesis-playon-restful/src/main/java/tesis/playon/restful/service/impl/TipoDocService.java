package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.TipoDoc;
import tesis.playon.restful.service.ITipoDocService;

@Service("tipoDocService")
@Transactional
public class TipoDocService implements ITipoDocService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void save(TipoDoc tipoDoc) {
	Session session = sessionFactory.getCurrentSession();
	session.save(tipoDoc);
    }

    @Override
    public void update(TipoDoc tipoDoc) {
	Session session = sessionFactory.getCurrentSession();
	session.update(tipoDoc);
    }

    @Override
    public void delete(TipoDoc tipoDoc) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(tipoDoc);
    }

    @Override
    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	Session session = sessionFactory.getCurrentSession();
	TipoDoc tipoDoc = (TipoDoc) session.get(TipoDoc.class, nombreTipoDoc);
	return tipoDoc;
    }

    @Override
    public List<TipoDoc> findAll() {
	List<TipoDoc> lista = new ArrayList<TipoDoc>();
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("FROM  TipoDoc");
	if (!query.list().isEmpty()) {
	    for (Object obj : query.list()) {
		lista.add((TipoDoc) obj);
	    }
	}
	return lista;
    }
}