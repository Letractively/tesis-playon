package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.TransaccionPlaya;

public interface ITransaccionPlayaDao {

    void save(TransaccionPlaya transaccionPlaya);

    void update(TransaccionPlaya transaccionPlaya);

    void delete(TransaccionPlaya transaccionPlaya);

    List<TransaccionPlaya> findAll();

    TransaccionPlaya findByTransaccionPlayaID(int id);

    List<TransaccionPlaya> findTransaccionesNoLiquidadas();

    List<TransaccionPlaya> findByCuentaPlaya(CuentaPlaya cuentaPlaya);

    List<TransaccionPlaya> findNoLiquidadasByCuentaPlaya(CuentaPlaya cuentaPlaya);

    // List<TransaccionPlaya> findByPlaya(Playa playa);

    List<TransaccionPlaya> findTransaccionesByCuentaPlaya(CuentaPlaya cuentaPlaya);

}
