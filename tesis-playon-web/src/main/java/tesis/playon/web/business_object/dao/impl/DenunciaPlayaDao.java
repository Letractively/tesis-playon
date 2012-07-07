/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IDenunciaPlayaDao;
import tesis.playon.web.model.DenunciaPlaya;

/**
 * @author garribere
 *
 */
@Repository("denunciaPlayaDao")
public class DenunciaPlayaDao extends CustomHibernateDaoSupport implements IDenunciaPlayaDao{
    
    public void save(DenunciaPlaya denunciaPlaya) {
	getHibernateTemplate().save(denunciaPlaya);
    }

    public void update(DenunciaPlaya denunciaPlaya) {
	getHibernateTemplate().update(denunciaPlaya);
    }

    public void delete(DenunciaPlaya denunciaPlaya) {
	getHibernateTemplate().delete(denunciaPlaya);
    }

    public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
	List<?> list = getHibernateTemplate().find("from DenunciaPlaya where asunto=?", asuntoDenunciaPlaya);
	return (DenunciaPlaya) list.get(0);
    }

}
