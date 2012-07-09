package tesis.playon.web.business_object.dao;

import java.util.List;


import tesis.playon.web.model.TipoEstadia;

public interface ITipoEstadiaDao {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia);

    List<TipoEstadia> findAll();

}
