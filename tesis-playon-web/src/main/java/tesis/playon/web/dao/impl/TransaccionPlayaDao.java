package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.TransaccionPlaya;

/**
 * 
 * @author alejandro
 *
 */
@Repository("transaccionPlayaDao")
public class TransaccionPlayaDao extends CustomHibernateDaoSupport implements ITransaccionPlayaDao {

    public void save(TransaccionPlaya transaccionPlaya) {
	getHibernateTemplate().save(transaccionPlaya);
    }

    public void update(TransaccionPlaya transaccionPlaya) {
	getHibernateTemplate().update(transaccionPlaya);
    }

    public void delete(TransaccionPlaya transaccionPlaya) {
	getHibernateTemplate().delete(transaccionPlaya);
    }

    public List<TransaccionPlaya> findAll(){
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getHibernateTemplate().find("from TransaccionPlaya");
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya)object);
	}
	return transaccionPlaya;
    }

}
