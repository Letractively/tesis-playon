package tesis.playon.restful.dao;

import java.util.List;

import tesis.playon.restful.domain.EstadoDenuncia;

public interface IEstadoDenunciaDao {

    void save(EstadoDenuncia estadoDenuncia);

    void update(EstadoDenuncia estadoDenuncia);

    void delete(EstadoDenuncia estadoDenuncia);

    EstadoDenuncia findByNombreEstadoDenuncia(String nombreDenuncia);

    List<EstadoDenuncia> findAll();
}
