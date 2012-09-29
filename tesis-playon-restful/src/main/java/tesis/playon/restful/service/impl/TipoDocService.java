package tesis.playon.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tesis.playon.restful.dao.ITipoDocDao;
import tesis.playon.restful.domain.TipoDoc;
import tesis.playon.restful.service.ITipoDocService;

@Service("tipoDocService")
public class TipoDocService implements ITipoDocService {

    @Autowired
    private ITipoDocDao tipoDocDao;

    @Override
    public void save(TipoDoc tipoDoc) {
	tipoDocDao.save(tipoDoc);
    }

    @Override
    public void update(TipoDoc tipoDoc) {
	tipoDocDao.update(tipoDoc);
    }

    @Override
    public void delete(TipoDoc tipoDoc) {
	tipoDocDao.delete(tipoDoc);
    }

    @Override
    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc) {
	return tipoDocDao.findByNombreTipoDoc(nombreTipoDoc);
    }

    @Override
    public List<TipoDoc> findAll() {
	return tipoDocDao.findAll();
    }

}