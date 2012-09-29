package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.ITransaccionPlayaDao;
import tesis.playon.restful.domain.TransaccionPlaya;

public class TransaccionPlayaDao extends HibernateDaoSupport implements ITransaccionPlayaDao {

    @Override
    public void save(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().save(transaccionPlaya);
    }

    @Override
    public void update(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().update(transaccionPlaya);
    }

    @Override
    public void delete(TransaccionPlaya transaccionPlaya) {
	getSessionFactory().getCurrentSession().delete(transaccionPlaya);
    }

    @Override
    public List<TransaccionPlaya> findAll() {
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from TransaccionPlaya").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }

}