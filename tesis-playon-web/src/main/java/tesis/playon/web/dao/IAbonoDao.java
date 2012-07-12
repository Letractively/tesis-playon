package tesis.playon.web.dao;

import java.util.List;

import tesis.playon.web.model.Abono;

/**
 * Interfaz DAO de Abono.
 * 
 * @author alejandro
 * 
 */
public interface IAbonoDao {

    void save(Abono abono);

    void update(Abono abono);

    void delete(Abono abono);

    // no se que findBy... poner.
    
    List<Abono> findAll();
}
