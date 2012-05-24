package edu.utn.frc.tesis_playon_web.business_object;

import edu.utn.frc.tesis_playon_web.model.Cliente;

public interface ClienteBo {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByClienteDni(String clienteDni);

}
