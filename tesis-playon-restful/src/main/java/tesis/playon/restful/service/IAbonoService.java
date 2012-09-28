package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Abono;

public interface IAbonoService {

    public void save(Abono abono);

    public void update(Abono abono);

    public void delete(Abono abono);

    List<Abono> findAll();
}