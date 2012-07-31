package tesis.playon.web.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IPlayaDao;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IPlayaService;
/**
 * 
 * @author pablo
 *
 */
@Transactional(readOnly = true)
public class PlayaService implements IPlayaService {

    IPlayaDao playaDao;
    
    @Transactional(readOnly = false)
    @Override
    public void save(Playa playa) {
	// TODO Auto-generated method stub
	getPlayaDao().save(playa);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Playa playa) {
	// TODO Auto-generated method stub
	getPlayaDao().update(playa);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Playa playa) {
	// TODO Auto-generated method stub
	getPlayaDao().delete(playa);
    }


    @Override
    public Playa findByNombreComercial(String nombreComercial) {
	// TODO Auto-generated method stub
	return getPlayaDao().findByNombreComercial(nombreComercial);
    }

    @Override
    public Playa findByRazonSocial(String razonSocial) {
	// TODO Auto-generated method stub
	return getPlayaDao().findByRazonSocial(razonSocial);
    }

    @Override
    public List<Playa> findAll() {
	// TODO Auto-generated method stub
	return getPlayaDao().findAll();
    }

    public IPlayaDao getPlayaDao() {
        return playaDao;
    }

    public void setPlayaDao(IPlayaDao playaDao) {
        this.playaDao = playaDao;
    }
    
    @Override
    public List <Playa> findPlayasCercanas(Double longitud, Double latitud, int distancia ){
	return getPlayaDao().findPlayasCercanas(longitud, latitud, distancia);
    }

    @Override
    public List<Playa> findPlayasPendientes(EstadoPlaya estado) {
	// TODO Auto-generated method stub
	return getPlayaDao().findPlayasPendientes(estado);
    }

}
