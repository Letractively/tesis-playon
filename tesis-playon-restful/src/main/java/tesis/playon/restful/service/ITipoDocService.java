package tesis.playon.restful.service;

import java.util.List;

import tesis.playon.restful.domain.TipoDoc;

public interface ITipoDocService {

    public void save(TipoDoc tipoDoc);

    public void update(TipoDoc tipoDoc);

    public void delete(TipoDoc tipoDoc);

    public TipoDoc findByNombreTipoDoc(String nombreTipoDoc);

    public List<TipoDoc> findAll();
}