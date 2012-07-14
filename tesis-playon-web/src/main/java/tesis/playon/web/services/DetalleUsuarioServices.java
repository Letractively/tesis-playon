package tesis.playon.web.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.web.dao.impl.UsuarioDao;
import tesis.playon.web.model.Usuario;

//@Service
//@Transactional(readOnly = true)
public class DetalleUsuarioServices {
    //
    // @Autowired
    // private UsuarioDao usuarioDao;
    //
    // public Usuario loadUserByUsername(String nombre) throws UsernameNotFoundException {
    // try {
    // Usuario usuario = new Usuario();
    // usuario = usuarioDao.findByNombreUsuario(nombre);
    //
    // // boolean enabled = true;
    // // boolean accountNonExpired = true;
    // // boolean credentialsNonExpired = true;
    // // boolean accountNonLocked = true;
    //
    // return usuario;
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // }
    //
    // public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
    // List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
    // return authList;
    // }
    //
    // public List<String> getRoles(Integer role) {
    // List<String> roles = new ArrayList<String>();
    //
    // if (role.intValue() == 1) {
    // roles.add("ROLE_USER");
    // roles.add("ROLE_ADMIN");
    //
    // } else if (role.intValue() == 2) {
    // roles.add("ROLE_USER");
    // }
    // return roles;
    // }
    //
    // public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
    // List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    // for (String role : roles) {
    // authorities.add(new SimpleGrantedAuthority(role));
    // }
    // return authorities;
    // }
}