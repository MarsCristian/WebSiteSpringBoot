package br.ufscar.dc.dsw.SiteConsultas.domain;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Paciente")
public class Paciente extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.paciente.email}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank(message = "{NotBlank.paciente.senha}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String senha;

    @NotBlank(message = "{NotNull.paciente.cpf}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cpf;

    @NotBlank(message = "{NotNull.paciente.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotNull.paciente.telefone}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String telefone;

    @NotBlank(message = "{NotNull.paciente.sexo}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String sexo;

    @NotBlank(message = "{NotNull.paciente.dataNascimento}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String dataNascimento;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}