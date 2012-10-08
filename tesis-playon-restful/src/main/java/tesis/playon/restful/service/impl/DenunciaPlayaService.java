package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.DenunciaPlaya;
import tesis.playon.restful.service.IDenunciaPlayaService;

@Service("denunciaPlayaService")
@Transactional
public class DenunciaPlayaService implements IDenunciaPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(DenunciaPlaya denunciaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.save(denunciaPlaya);
    }

    @Override
    public void update(DenunciaPlaya denunciaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.update(denunciaPlaya);
    }

    @Override
    public void delete(DenunciaPlaya denunciaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(denunciaPlaya);
    }

    @Override
    public DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from DenunciaPlaya where asunto=?")
		.setParameter(0, asuntoDenunciaPlaya).list();
	return (DenunciaPlaya) list.get(0);
    }

    @Override
    public List<DenunciaPlaya> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<DenunciaPlaya> denuncias = new ArrayList<DenunciaPlaya>();
	List<?> list = session.createQuery("from DenunciaPlaya").list();
	for (Object object : list) {
	    denuncias.add((DenunciaPlaya) object);
	}
	return denuncias;
    }
}