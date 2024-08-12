package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;

import java.util.List;

public interface IConsultaService {
    Consulta buscarPorId(Long id);
    List<Consulta>  buscarPorPaciente(Paciente id);
    List<Consulta>  buscarPorMedico(Medico id);

    List<Consulta> buscarTodos();

    void salvar(Consulta consulta);

    void excluir(Long id);
}
