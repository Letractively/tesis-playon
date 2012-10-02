package tesis.playon.web.service;

import java.util.List;

import tesis.playon.web.model.TransaccionPlaya;

public interface ITransaccionPlayaService {

    public void save(TransaccionPlaya transaccionPlaya);

    public void update(TransaccionPlaya transaccionPlaya);

    public void delete(TransaccionPlaya transaccionPlaya);

    public TransaccionPlaya findByTransaccionPlayaid(int transaccionID);

    public List<TransaccionPlaya> findAll();

}
