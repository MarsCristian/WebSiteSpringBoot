package br.ufscar.dc.dsw.SiteConsultas.dao;

import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, Long> {
    Medico findById(long id);
    Medico findBycrm(String crm);
    List<Medico> findAll();
    Medico save(Medico medico);
    void deleteById(Long id);
}