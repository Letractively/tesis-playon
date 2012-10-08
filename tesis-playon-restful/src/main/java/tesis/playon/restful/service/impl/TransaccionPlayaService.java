package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.TransaccionPlaya;
import tesis.playon.restful.service.ITransaccionPlayaService;

@Service("transaccionPlayaService")
@Transactional
public class TransaccionPlayaService implements ITransaccionPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(TransaccionPlaya transaccionPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.save(transaccionPlaya);
    }

    @Override
    public void update(TransaccionPlaya transaccionPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.update(transaccionPlaya);
    }

    @Override
    public void delete(TransaccionPlaya transaccionPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(transaccionPlaya);
    }

    @Override
    public List<TransaccionPlaya> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<TransaccionPlaya> transaccionPlaya = new ArrayList<TransaccionPlaya>();
	List<?> list = session.createQuery("from TransaccionPlaya").list();
	for (Object object : list) {
	    transaccionPlaya.add((TransaccionPlaya) object);
	}
	return transaccionPlaya;
    }
}