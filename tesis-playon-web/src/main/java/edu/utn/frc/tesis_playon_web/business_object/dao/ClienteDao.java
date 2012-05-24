package edu.utn.frc.tesis_playon_web.business_object.dao;

import edu.utn.frc.tesis_playon_web.model.Cliente;

public interface ClienteDao {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByClienteDni(String clienteDni);

}
