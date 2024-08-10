package br.ufscar.dc.dsw.SiteConsultas.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@Entity
@Table(name = "Consulta")
public class Consulta  extends AbstractEntity<Long> {

    @NotNull(message = "senhora")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "sem medico")
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @NotNull(message = "sem paciente")
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false, unique = true)
    private String consultaKey;


    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Consulta [id=" + getId() + ", dataHora=" + dataHora + ", medico=" + medico.getNome() + ", paciente=" + paciente.getNome() + "]";
    }
    private void setConsultaKey() {
        if (this.medico != null && this.paciente != null && this.dataHora != null) {
            this.consultaKey = this.dataHora.toString() + "-" + this.medico.getCrm() + "-" + this.paciente.getCpf();
        }//DE OLHO QUANDO CPF E CRM NAO FOR STRING
    }
    public String getConsultaKey() {
        return consultaKey;
    }

}
