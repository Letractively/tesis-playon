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
	getPlayaDao().save(playa);
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Playa playa) {
	getPlayaDao().update(playa);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Playa playa) {
	getPlayaDao().delete(playa);
    }


    @Override
    public Playa findByNombreComercial(String nombreComercial) {
	return getPlayaDao().findByNombreComercial(nombreComercial);
    }

    @Override
    public Playa findByRazonSocial(String razonSocial) {
	return getPlayaDao().findByRazonSocial(razonSocial);
    }
    
    @Override
    public Playa findById(int idPlaya) {
	return getPlayaDao().findById(idPlaya);
    }

    @Override
    public List<Playa> findAll() {
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
    public List<Playa> findByEstado(EstadoPlaya estado) {
	// TODO Auto-generated method stub
	return getPlayaDao().findByEstado(estado);
    }
    
    @Override
    public boolean existeEmail(String email){
	return getPlayaDao().existeEmail(email);
    }
}
