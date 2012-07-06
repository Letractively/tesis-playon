package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.TipoEstadia;

public interface ITipoEstadiaDao {

    void save(TipoEstadia tipoEstadia);

    void update(TipoEstadia tipoEstadia);

    void delete(TipoEstadia tipoEstadia);

    Barrio findByNombreTipoEstadia(String nombreTipoEstadia);

}
