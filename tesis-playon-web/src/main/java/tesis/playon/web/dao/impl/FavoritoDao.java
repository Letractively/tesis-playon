package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IFavoritoDao;
import tesis.playon.web.model.Favorito;

/**
 * @author Pablo
 * 
 */
@Repository("favoritoDao")
public class FavoritoDao extends CustomHibernateDaoSupport implements IFavoritoDao {

    public void save(Favorito favorito) {
	getHibernateTemplate().save(favorito);
    }

    public void update(Favorito favorito) {
	getHibernateTemplate().update(favorito);
    }

    public void delete(Favorito favorito) {
	getHibernateTemplate().delete(favorito);
    }

    // public Favorito findByPlayas(String playaID) {
    // List<?> list = getHibernateTemplate().find("from Favorito where nombre=?", playaID);
    // return (Favorito) list.get(0);
    // }
    
    public List<Favorito> findAll(){
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getHibernateTemplate().find("from Favorito");
	for (Object object : list) {
	    favoritos.add((Favorito)object);
	}
	return favoritos;
    }
}
