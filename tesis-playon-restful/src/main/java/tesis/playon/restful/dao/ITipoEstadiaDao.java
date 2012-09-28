package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.TipoEstadia;

public interface ITipoEstadiaDao {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia);

    List<TipoEstadia> findAll();

}
