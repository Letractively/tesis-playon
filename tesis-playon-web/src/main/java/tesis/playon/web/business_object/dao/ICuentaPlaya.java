package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.CuentaPlaya;

public interface ICuentaPlaya {

    void save(CuentaPlaya cuentaPlaya);

    void update(CuentaPlaya cuentaPlaya);

    void delete(CuentaPlaya cuentaPlaya);

    CuentaPlaya findByNroCuenta(String nroCuenta);

}
