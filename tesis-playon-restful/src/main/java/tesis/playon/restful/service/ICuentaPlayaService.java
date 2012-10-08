package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.CuentaPlaya;
import tesis.playon.restful.domain.Playa;

public interface ICuentaPlayaService {

    void save(CuentaPlaya cuentaPlaya);

    void update(CuentaPlaya cuentaPlaya);

    void delete(CuentaPlaya cuentaPlaya);

    List<CuentaPlaya> findAll();

    CuentaPlaya findByNroCuenta(Integer nroCuenta);

    CuentaPlaya findByPlaya(Playa playa);

}
