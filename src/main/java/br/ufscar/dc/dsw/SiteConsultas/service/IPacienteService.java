package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPacienteService {
    Paciente buscarPorId(Long id);
    Paciente buscarPorNome(String nome);
    Paciente buscarPorEmail(String email);
    List<Paciente> buscarTodos();
    void salvar(Paciente paciente);
    void excluir(Long id);
    //boolean PacienteTemConsultas(Long id); CONSULTAS
}
