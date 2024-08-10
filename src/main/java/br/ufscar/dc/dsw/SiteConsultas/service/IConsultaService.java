package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;

import java.util.List;

public interface IConsultaService {
    Consulta buscarPorId(Long id);

    List<Consulta> buscarTodos();

    void salvar(Consulta consulta);

    void excluir(Long id);
}
