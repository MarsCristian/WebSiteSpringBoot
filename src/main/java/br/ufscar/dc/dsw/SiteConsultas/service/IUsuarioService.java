package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.domain.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarPorId(Long id);
    List<Usuario> buscarTodos();
    void salvar(Usuario usuario);
    void excluir(Long id);
}
