package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IAbonoDao;
import tesis.playon.web.model.Abono;

@Repository("abonoDao")
public class AbonoDao extends CustomHibernateDaoSupport implements IAbonoDao {

    public void save(Abono abono) {
	getHibernateTemplate().save(abono);
    }

    public void update(Abono abono) {
	getHibernateTemplate().update(abono);
    }

    public void delete(Abono abono) {
	getHibernateTemplate().delete(abono);
    }
    
    public List<Abono> findAll(){
	List<Abono> abonos = new ArrayList<Abono>();
	List<?> list = getHibernateTemplate().find("from Abono");
	for (Object obj : list) {
	    abonos.add((Abono) obj);
	}
	return abonos;
    }

}
