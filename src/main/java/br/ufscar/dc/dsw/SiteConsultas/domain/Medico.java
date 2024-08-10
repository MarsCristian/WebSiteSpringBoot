package br.ufscar.dc.dsw.SiteConsultas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Medico")
public class Medico extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.medico.email}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank(message = "{NotBlank.medico.senha}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String senha;

    @NotBlank(message = "{NotNull.medico.crm}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String crm;

    @NotBlank(message = "{NotNull.medico.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotNull.medico.especialidade}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String especialidade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Medico(String email, String senha, String crm, String nome, String especialidade) {
        this.email = email;
        this.senha = senha;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Medico() {
    }
}