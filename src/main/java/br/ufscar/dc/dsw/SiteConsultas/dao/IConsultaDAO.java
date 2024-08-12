package br.ufscar.dc.dsw.SiteConsultas.dao;

import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
    Consulta findById(long id);
    Consulta findByconsultaKey(String consultaKey);
    @Query("SELECT Consulta FROM Consulta Consulta WHERE Consulta.medico = :medico")
    List<Consulta> findBymedico(Medico medico);
    @Query("SELECT Consulta FROM Consulta Consulta WHERE Consulta.paciente = :paciente")
    List<Consulta> findBypaciente(Paciente paciente);
    List<Consulta> findAll();
    Consulta save(Consulta consulta);
    void deleteById(Long id);
    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

}
