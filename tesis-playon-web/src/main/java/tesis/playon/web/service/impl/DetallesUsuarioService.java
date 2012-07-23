package tesis.playon.web.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.IRolUsuarioDao;
import tesis.playon.web.dao.IUsuarioDao;
import tesis.playon.web.dao.IUsuarioSistemaDao;
import tesis.playon.web.model.RolUsuario;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.model.UsuarioSistema;

@Service("detallesUsuarioService")
@Transactional(readOnly = true)
public class DetallesUsuarioService implements UserDetailsService {

    IRolUsuarioDao rolusuario;

    IUsuarioDao usuarioDao;

    IUsuarioSistemaDao usuarioSitemaDao;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {

	UserDetails detalleUsuario = null;
	Usuario usuario = getUsuarioDao().findByNombreUsuario(nombreUsuario);
	if (usuario == null)
	    throw new UsernameNotFoundException("Usuario no encontyrado");

	// String nomUsuario = usuario.getNombreUser();
	// String password = usuario.getPassword();

	UsuarioSistema usuarioSistema = getUsuarioSitemaDao().findByNombreUsuarioSistema(usuario);
	RolUsuario rolUsuario = usuarioSistema.getRolUsuario();

	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	authorities.add(new GrantedAuthorityImpl(rolUsuario.getNombre()));

	return detalleUsuario;
    }

    public IRolUsuarioDao getRolusuario() {
	return rolusuario;
    }

    public void setRolusuario(IRolUsuarioDao rolusuario) {
	this.rolusuario = rolusuario;
    }

    public IUsuarioDao getUsuarioDao() {
	return usuarioDao;
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
	this.usuarioDao = usuarioDao;
    }

    public IUsuarioSistemaDao getUsuarioSitemaDao() {
	return usuarioSitemaDao;
    }

    public void setUsuarioSitemaDao(IUsuarioSistemaDao usuarioSitemaDao) {
	this.usuarioSitemaDao = usuarioSitemaDao;
    }

}
