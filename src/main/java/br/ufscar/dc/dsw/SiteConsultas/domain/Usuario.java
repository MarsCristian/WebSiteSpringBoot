package br.ufscar.dc.dsw.SiteConsultas.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long>{

    @NotBlank(message = "{NotBlank.usuario.email}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank(message = "{NotBlank.usuario.senha}")
    //@Size(max = 60)
    @Column(nullable = false, length = 256)
    private String senha;

    @NotBlank(message = "{NotBlank.usuario.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotBlank.usuario.papel}")
    @Size(max = 60)
    @Column(nullable = false, unique = false, length = 60)
    private String papel;

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario(String email, String senha, String nome, String papel) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.papel = papel;
    }

    public Usuario() {}
}
