package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Comentario;

public interface IComentarioService {

    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Comentario comentario);

    List<Comentario> findByPlaya(String nombreComercialPlaya);

    List<Comentario> findAll();
}
