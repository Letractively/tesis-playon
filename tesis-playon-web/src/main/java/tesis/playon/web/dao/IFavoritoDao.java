package tesis.playon.web.dao;

import tesis.playon.web.model.Favorito;

/**
 * @author Pablo
 *
 */
public interface IFavoritoDao {

    void save(Favorito favorito);

    void update(Favorito favorito);

    void delete(Favorito favorito);

    //Favorito findByPlayas(String playaID);
}
