/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IFotoDao;
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
}
