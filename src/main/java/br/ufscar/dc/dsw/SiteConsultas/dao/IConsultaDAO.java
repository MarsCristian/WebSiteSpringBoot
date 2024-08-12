package br.ufscar.dc.dsw.SiteConsultas.dao;

import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {
    Consulta findById(long id);
    Consulta findByconsultaKey(String consultaKey);
    List<Consulta> findAll();
    Consulta save(Consulta consulta);
    void deleteById(Long id);
    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

}
