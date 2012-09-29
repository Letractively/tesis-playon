package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ITipoDocDao;
import tesis.playon.restful.domain.TipoDoc;

public class TipoDocDao extends HibernateDaoSupport implements ITipoDocDao {

    @Override
    public void save(TipoDoc tipoDoc) {
	getSessionFactory().getCurrentSession().save(tipoDoc);
    }

    @Override
    public void update(TipoDoc tipoDoc) {
	getSessionFactory().getCurrentSession().update(tipoDoc);
    }

    @Override
    public void delete(TipoDoc tipoDoc) {
	getSessionFactory().getCurrentSession().delete(tipoDoc);
    }

    @Override
    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoDoc where nombre=?")
		.setParameter(0, nombreTipoDoc).list();
	return (TipoDoc) list.get(0);
    }

    @Override
    public List<TipoDoc> findAll() {
	List<TipoDoc> documentos = new ArrayList<TipoDoc>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TipoDoc order by nombre").list();
	for (Object object : list) {
	    documentos.add((TipoDoc) object);
	}
	return documentos;
    }

}