package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IDenunciaPlayaDao;
import tesis.playon.restful.domain.DenunciaPlaya;

@Repository("denunciaPlayaDao")
public class DenunciaPlayaDao extends HibernateDaoSupport implements IDenunciaPlayaDao {

    @Override
    public void save(DenunciaPlaya denunciaPlaya) {
	getSessionFactory().getCurrentSession().save(denunciaPlaya);
    }

    @Override
    public void update(DenunciaPlaya denunciaPlaya) {
	getSessionFactory().getCurrentSession().update(denunciaPlaya);
    }

    @Override
    public void delete(DenunciaPlaya denunciaPlaya) {
	getSessionFactory().getCurrentSession().delete(denunciaPlaya);
    }

    @Override
    public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DenunciaPlaya where asunto=?")
		.setParameter(0, asuntoDenunciaPlaya).list();
	return (DenunciaPlaya) list.get(0);
    }

    @Override
    public List<DenunciaPlaya> findAll() {
	List<DenunciaPlaya> denuncias = new ArrayList<DenunciaPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DenunciaPlaya").list();
	for (Object object : list) {
	    denuncias.add((DenunciaPlaya) object);
	}
	return denuncias;
    }
    
}