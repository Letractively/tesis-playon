package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.TipoEstadia;

public interface ITipoEstadiaService {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia);

    List<TipoEstadia> findAll();

}
