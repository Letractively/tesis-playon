package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ITipoDocDao;
import tesis.playon.restful.domain.TipoDoc;

public class TipoDocDao extends HibernateDaoSupport implements ITipoDocDao {

    @Override
    public void save(TipoDoc tipoDoc) {
	getHibernateTemplate().save(tipoDoc);
    }

    @Override
    public void update(TipoDoc tipoDoc) {
	getHibernateTemplate().update(tipoDoc);
    }

    @Override
    public void delete(TipoDoc tipoDoc) {
	getHibernateTemplate().delete(tipoDoc);
    }

    @Override
    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	List<?> list = getHibernateTemplate(). find("from TipoDoc where nombre=?", nombreTipoDoc);
	return (TipoDoc) list.get(0);
    }

    @Override
    public List<TipoDoc> findAll() {
	List<TipoDoc> tipoDocumentos = new ArrayList<TipoDoc>();
	List<?> list = getHibernateTemplate().find("from TipoDoc");
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		tipoDocumentos.add((TipoDoc) obj);
	    }
	    return tipoDocumentos;
	}
	return null;
    }

}