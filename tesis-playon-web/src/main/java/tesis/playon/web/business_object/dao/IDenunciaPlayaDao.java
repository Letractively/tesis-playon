/**
 * 
 */
package tesis.playon.web.business_object.dao;

import tesis.playon.web.model.DenunciaPlaya;

/**
 * @author garribere
 *
 */
public interface IDenunciaPlayaDao {

    void save(DenunciaPlaya denunciaPlaya);

    void update(DenunciaPlaya denunciaPlaya);

    void delete(DenunciaPlaya denunciaPlaya);

    DenunciaPlaya findByAsuntoDenunciaPlaya(String asuntoDenunciaPlaya);
}
