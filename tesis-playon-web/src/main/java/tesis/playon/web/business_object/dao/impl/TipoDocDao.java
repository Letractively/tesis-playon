package tesis.playon.web.business_object.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.ITipoDocDao;
import tesis.playon.web.model.TipoDoc;

/**
 * 
 * @author garribere
 *
 */
@Repository("tipoDocDao")
public class TipoDocDao extends CustomHibernateDaoSupport implements ITipoDocDao {

    public void save(TipoDoc tipoDoc) {
	getHibernateTemplate().save(tipoDoc);
    }

    public void update(TipoDoc tipoDoc) {
	getHibernateTemplate().update(tipoDoc);
    }

    public void delete(TipoDoc tipoDoc) {
	getHibernateTemplate().delete(tipoDoc);
    }

    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	List<?> list = getHibernateTemplate().find("from TipoDoc where nombre=?", nombreTipoDoc);
	return (TipoDoc) list.get(0);
    }
    
    public List<TipoDoc> findAll(){
	List<TipoDoc> documentos = new ArrayList<TipoDoc>();
	List<?> list = getHibernateTemplate().find("from TipoDoc");
	for (Object object : list) {
	    documentos.add((TipoDoc)object);
	}
	return documentos;
    }
}
