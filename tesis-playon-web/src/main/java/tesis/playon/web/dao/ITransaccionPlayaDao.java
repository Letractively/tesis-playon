package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.TransaccionPlaya;

public interface ITransaccionPlayaDao {

    void save(TransaccionPlaya transaccionPlaya);

    void update(TransaccionPlaya transaccionPlaya);

    void delete(TransaccionPlaya transaccionPlaya);

    List<TransaccionPlaya> findAll();

    TransaccionPlaya findByTransaccionPlayaID(int id);
}
