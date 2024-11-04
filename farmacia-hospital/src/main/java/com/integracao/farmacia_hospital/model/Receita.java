package com.integracao.farmacia_hospital.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEnvio;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Medicamento> medicamentos;

    private String assinaturaDigital;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
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

    public List<Medicamento> getMedicamentos() {
        return this.medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }

    public void setAssinaturaDigital(String assinaturaDigital) {
        this.assinaturaDigital = assinaturaDigital;
    }

    public String dadosParaAssinatura() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(this.id)
                .append("&dataEnvio=").append(this.dataEnvio)
                .append("&medicoId=").append(this.medico.getId())
                .append("&pacienteId=").append(this.paciente.getId());

        // Verifica se a lista de medicamentos não é nula
        if (this.medicamentos != null) {
            for (Medicamento medicamento : this.medicamentos) {
                sb.append("&medicamentoId=").append(medicamento.getId())
                        .append("&nome=").append(medicamento.getNome())
                        .append("&dosagem=").append(medicamento.getDosagem())
                        .append("&quantidade=").append(medicamento.getQuantidade());
                // Inclua outros campos conforme necessário
            }
        }

        return sb.toString();
    }
}
