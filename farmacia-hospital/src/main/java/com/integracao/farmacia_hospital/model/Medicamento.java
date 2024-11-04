package com.integracao.farmacia_hospital.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dosagem;
    private Integer quantidade;
    private String viaAdministracao;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    @JsonBackReference
    private Receita receita;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getViaAdministracao() {
        return viaAdministracao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
