package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.dao.IMedicoDAO;
import br.ufscar.dc.dsw.SiteConsultas.dao.IPacienteDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

    @Autowired
    IPacienteDAO dao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void salvar(Paciente paciente) {
        // Criptografar a senha antes de salvar
        String encodedPassword = passwordEncoder.encode(paciente.getSenha());
        paciente.setSenha(encodedPassword);
        dao.save(paciente);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public Paciente buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Paciente> buscarTodos() {
        return dao.findAll();
    }
// CONSULTAS
//    @Transactional(readOnly = true)
//    public boolean MedicoTemConsultas(Long id) {
//        return !dao.findById(id.longValue()).getConsultas().isEmpty();
//    }
}
