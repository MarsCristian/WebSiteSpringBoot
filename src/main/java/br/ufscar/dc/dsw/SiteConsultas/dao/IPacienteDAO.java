package br.ufscar.dc.dsw.SiteConsultas.dao;

import java.util.List;

import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import org.springframework.data.repository.CrudRepository;


@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, Long>{
    Paciente findById(long id);
    Paciente findBycpf(String cpf);
    List<Paciente> findAll();
    Paciente save(Paciente paciente);
    void deleteById(Long id);
}