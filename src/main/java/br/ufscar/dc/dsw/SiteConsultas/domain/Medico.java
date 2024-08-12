package br.ufscar.dc.dsw.SiteConsultas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Medico")
public class Medico extends Usuario {


    @NotBlank(message = "{NotBlank.medico.crm}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String crm;

    @NotBlank(message = "{NotBlank.medico.especialidade}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String especialidade;


    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Medico(String email, String senha, String nome, String papel, String crm, String especialidade) {
        super(email, senha, nome, papel);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {}
}