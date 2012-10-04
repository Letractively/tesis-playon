package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.ITransaccionPlayaDao;
import tesis.playon.web.model.TransaccionPlaya;

@Transactional(readOnly = true)
public class TransaccionPlayaService {
    ITransaccionPlayaDao transaccionPlayaDao;

    @Transactional(readOnly = false)
    public void save(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().save(TransaccionPlaya);
    }

    @Transactional(readOnly = false)
    public void update(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().update(TransaccionPlaya);
    }

    @Transactional(readOnly = false)
    public void delete(TransaccionPlaya TransaccionPlaya) {
	getTransaccionPlayaDao().delete(TransaccionPlaya);
    }

    public List<TransaccionPlaya> findAll() {
	return getTransaccionPlayaDao().findAll();
    }

    public TransaccionPlaya findByTransaccionPlayaID(int transaccionPlayaID) {
	return getTransaccionPlayaDao().findByTransaccionPlayaID(transaccionPlayaID);
    }

    public ITransaccionPlayaDao getTransaccionPlayaDao() {
	return transaccionPlayaDao;
    }

    public void setTransaccionPlayaDao(ITransaccionPlayaDao transaccionPlayaDao) {
	this.transaccionPlayaDao = transaccionPlayaDao;
    }

}
