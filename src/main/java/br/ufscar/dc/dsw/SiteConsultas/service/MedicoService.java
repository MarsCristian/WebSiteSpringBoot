package br.ufscar.dc.dsw.SiteConsultas.service;
import java.util.List;

import br.ufscar.dc.dsw.SiteConsultas.dao.IMedicoDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {

    @Autowired
    IMedicoDAO dao;

    public void salvar(Medico paciente) {
        dao.save(paciente);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Medico buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Medico> buscarTodos() {
        return dao.findAll();
    }
// CONSULTAS
//    @Transactional(readOnly = true)
//    public boolean MedicoTemConsultas(Long id) {
//        return !dao.findById(id.longValue()).getConsultas().isEmpty();
//    }
}