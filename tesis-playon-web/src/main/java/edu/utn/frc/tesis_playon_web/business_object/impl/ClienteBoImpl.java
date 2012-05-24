package edu.utn.frc.tesis_playon_web.business_object.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.utn.frc.tesis_playon_web.business_object.ClienteBo;
import edu.utn.frc.tesis_playon_web.business_object.dao.ClienteDao;
import edu.utn.frc.tesis_playon_web.model.Cliente;

public class ClienteBoImpl implements ClienteBo {

    @Autowired
    ClienteDao clienteDao;

    public void setClienteDao(ClienteDao clienteDao) {
	this.clienteDao = clienteDao;
    }

    public void save(Cliente cliente) {
	clienteDao.save(cliente);
    }

    public void update(Cliente cliente) {
	clienteDao.save(cliente);
    }

    public void delete(Cliente cliente) {
	clienteDao.save(cliente);
    }

    public Cliente findByClienteDni(String clienteDni) {
	return clienteDao.findByClienteDni(clienteDni);
    }

}
