package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IFotoDao;
import tesis.playon.web.model.Foto;

/**
 * @author Pablo
 *
 */
@Repository("fotoDao")
public class FotoDao extends CustomHibernateDaoSupport implements IFotoDao{

    public void save(Foto foto) {
	getHibernateTemplate().save(foto);
    }

    public void update(Foto foto) {
	getHibernateTemplate().update(foto);
    }

    public void delete(Foto foto) {
	getHibernateTemplate().delete(foto);
    }

    public Foto findByLinkFoto(String link) {
	List<?> list = getHibernateTemplate().find("from Foto where link=?", link);
	return (Foto) list.get(0);
    }
    
    public List<Foto> findAll(){
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = getHibernateTemplate().find("from Foto");
	for (Object object : list) {
	    fotos.add((Foto)object);
	}
	return fotos;
    }
}
