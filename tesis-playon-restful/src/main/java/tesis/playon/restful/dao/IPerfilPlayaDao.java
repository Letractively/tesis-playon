package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.domain.Playa;

public interface IPerfilPlayaDao {

    void save(PerfilPlaya perfilPlaya);

    void update(PerfilPlaya perfilPlaya);

    void delete(PerfilPlaya perfilPlaya);

    PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya);
    
    PerfilPlaya findByPlaya(Playa playa);
    
    List<PerfilPlaya> findAll();

}
