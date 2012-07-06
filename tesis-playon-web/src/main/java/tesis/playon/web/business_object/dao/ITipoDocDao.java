package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.TipoDoc;

public interface ITipoDocDao {

    void save(TipoDoc tipoDoc);

    void update(TipoDoc tipoDoc);

    void delete(TipoDoc tipoDoc);

    TipoDoc findByNombreTipoDoc(String nombreTipoDoc);

}
