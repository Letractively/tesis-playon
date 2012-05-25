package tesis.playon.web.business_object;

import tesis.playon.web.model.Cliente;

public interface ClienteBo {

    void save(Cliente cliente);

    void update(Cliente cliente);

    void delete(Cliente cliente);

    Cliente findByClienteDni(String clienteDni);

}
