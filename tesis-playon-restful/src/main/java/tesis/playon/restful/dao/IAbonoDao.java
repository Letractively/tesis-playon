package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.Abono;

public interface IAbonoDao {

    void save(Abono abono);

    void update(Abono abono);

    void delete(Abono abono);

    List<Abono> findAll();
}
