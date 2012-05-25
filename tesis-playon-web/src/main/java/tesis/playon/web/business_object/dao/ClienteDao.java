package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.Cliente;

public interface ClienteDao {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByClienteDni(String clienteDni);

}
