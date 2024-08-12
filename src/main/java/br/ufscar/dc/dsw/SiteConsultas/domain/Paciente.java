package br.ufscar.dc.dsw.SiteConsultas.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {



    @NotBlank(message = "{NotBlank.paciente.cpf}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cpf;

    @NotBlank(message = "{NotBlank.paciente.telefone}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String telefone;

    @NotBlank(message = "{NotBlank.paciente.sexo}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String sexo;

    @NotBlank(message = "{NotBlank.paciente.dataNascimento}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String dataNascimento;

    public Paciente() {

    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Paciente(String email, String senha, String nome, String papel, String cpf, String telefone, String sexo, String dataNascimento) {
        super(email, senha, nome, papel);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

}