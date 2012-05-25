package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.web.business_object.dao.ClienteDao;
import tesis.playon.web.model.Cliente;
import tesis.playon.web.util.CustomHibernateDaoSupport;


@Repository("clienteDao")
public class ClienteDaoImpl extends CustomHibernateDaoSupport implements ClienteDao {

    public void save(Cliente cliente) {
	getHibernateTemplate().save(cliente);
    }

    public void update(Cliente cliente) {
	getHibernateTemplate().update(cliente);
    }

    public void delete(Cliente cliente) {
	getHibernateTemplate().delete(cliente);
    }

    public Cliente findByClienteDni(String clienteDni) {
	List<?> list = getHibernateTemplate().find("from Cliente where clienteDni=?", clienteDni);
	return (Cliente)list.get(0);
    }
}
