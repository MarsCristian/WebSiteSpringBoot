package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO dao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return dao.findAll();
    }


    public void salvar(Usuario usuario) {
        // Criptografar a senha antes de salvar
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedPassword);
        dao.save(usuario);
    }


    public void excluir(Long id) {
        dao.deleteById(id);
    }
}
