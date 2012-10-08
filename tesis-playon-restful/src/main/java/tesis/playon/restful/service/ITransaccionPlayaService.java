package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.TransaccionPlaya;

public interface ITransaccionPlayaService {

    void save(TransaccionPlaya transaccionPlaya);

    void update(TransaccionPlaya transaccionPlaya);

    void delete(TransaccionPlaya transaccionPlaya);

    List<TransaccionPlaya> findAll();
}
