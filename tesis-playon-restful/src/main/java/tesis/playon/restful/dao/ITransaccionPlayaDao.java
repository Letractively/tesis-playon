package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.TransaccionPlaya;

public interface ITransaccionPlayaDao {

    void save(TransaccionPlaya transaccionPlaya);

    void update(TransaccionPlaya transaccionPlaya);

    void delete(TransaccionPlaya transaccionPlaya);

    List<TransaccionPlaya> findAll();
}
