package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IAbonoDao;
import tesis.playon.restful.domain.Abono;

@Repository("abonoDao")
public class AbonoDao extends HibernateDaoSupport implements IAbonoDao {

    @Override
    public void save(Abono abono) {
	getHibernateTemplate().save(abono);
    }

    @Override
    public void update(Abono abono) {
	getHibernateTemplate().update(abono);
    }

    @Override
    public void delete(Abono abono) {
	getHibernateTemplate().delete(abono);
    }

    @Override
    public List<Abono> findAll() {
	List<Abono> abonos = new ArrayList<Abono>();
	List<?> list = getHibernateTemplate().find("from Abono");
	if (!list.isEmpty()) {
	    for (Object obj : list) {
		abonos.add((Abono) obj);
	    }
	    return abonos;
	}
	return null;
    }
}
