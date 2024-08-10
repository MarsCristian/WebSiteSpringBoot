package br.ufscar.dc.dsw.SiteConsultas.service;

import br.ufscar.dc.dsw.SiteConsultas.dao.IConsultaDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Consulta> buscarTodos() {
        return dao.findAll();
    }


    public void salvar(Consulta consulta) {
        consulta.setConsultaKey();
        dao.save(consulta);
    }


    public void excluir(Long id) {
        dao.deleteById(id);
    }
}
