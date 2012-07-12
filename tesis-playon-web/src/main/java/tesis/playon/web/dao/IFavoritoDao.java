package tesis.playon.web.dao;

import java.util.List;

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
    
    List<Favorito> findAll();
}
