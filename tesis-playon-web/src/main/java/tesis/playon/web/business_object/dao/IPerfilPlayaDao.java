package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.PerfilPlaya;

public interface IPerfilPlayaDao {

    void save(PerfilPlaya perfilPlaya);

    void update(PerfilPlaya perfilPlaya);

    void delete(PerfilPlaya perfilPlaya);

    PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya);

}
