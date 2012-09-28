package tesis.playon.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tesis.playon.restful.dao.IAbonoDao;
import tesis.playon.restful.domain.Abono;
import tesis.playon.restful.service.IAbonoService;

@Service("abonoService")
public class AbonoService implements IAbonoService {

    @Autowired
    private IAbonoDao abonoDao;

    @Override
    public void save(Abono abono) {
	abonoDao.save(abono);
    }

    @Override
    public void update(Abono abono) {
	abonoDao.update(abono);
    }

    @Override
    public void delete(Abono abono) {
	abonoDao.delete(abono);
    }

    @Override
    public List<Abono> findAll() {
	return abonoDao.findAll();
    }

}