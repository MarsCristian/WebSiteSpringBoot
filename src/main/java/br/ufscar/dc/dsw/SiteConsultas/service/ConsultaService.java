package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.dao.IConsultaDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;

import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import br.ufscar.dc.dsw.SiteConsultas.exception.HorarioDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

    @Autowired
    IConsultaDAO dao;

    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Consulta>  buscarPorPaciente(Paciente id) {
        return dao.findBypaciente(id);
    }

    @Transactional(readOnly = true)
    public List<Consulta>  buscarPorMedico(Medico id) {
        return dao.findBymedico(id);
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarTodos() {
        return dao.findAll();
    }


    public void salvar(Consulta consulta) {
        consulta.setConsultaKey();
        if (existeConsultaNoMesmoHorario(consulta.getMedico().getId(), consulta.getDataHora())) {
            throw new HorarioDuplicadoException("O médico já tem uma consulta marcada para esse horário.");
        }

        try {
            dao.save(consulta);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new HorarioDuplicadoException("Já existe uma consulta marcada para esse horário.");
            } else {
                throw e;
            }
        }
    }

    private boolean existeConsultaNoMesmoHorario(Long medicoId, LocalDateTime dataHora) {
        return dao.existsByMedicoIdAndDataHora(medicoId, dataHora);
    }


    public void excluir(Long id) {
        dao.deleteById(id);
    }
}
