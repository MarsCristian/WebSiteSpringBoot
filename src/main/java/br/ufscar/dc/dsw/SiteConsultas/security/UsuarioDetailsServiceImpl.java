package br.ufscar.dc.dsw.SiteConsultas.security;

import br.ufscar.dc.dsw.SiteConsultas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = dao.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        System.out.println("Loading Username");
        System.out.println(usuario.getPapel());




        return new UsuarioDetails(usuario);
    }
}