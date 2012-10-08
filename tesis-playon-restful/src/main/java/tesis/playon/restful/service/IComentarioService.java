package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.Comentario;
import tesis.playon.restful.domain.Playa;

public interface IComentarioService {

    void save(Comentario comentario);

    void update(Comentario comentario);

    void delete(Comentario comentario);

    List<Comentario> findByPlaya(Playa playa);

    List<Comentario> findAll();
}
