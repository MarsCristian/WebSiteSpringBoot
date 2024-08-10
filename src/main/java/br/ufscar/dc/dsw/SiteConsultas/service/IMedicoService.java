package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;

import java.util.List;

public interface IMedicoService {
    Medico buscarPorId(Long id);

    List<Medico> buscarTodos();

    void salvar(Medico medico);

    void excluir(Long id);
    //boolean MedicoTemConsultas(Long id); CONSULTAS
}