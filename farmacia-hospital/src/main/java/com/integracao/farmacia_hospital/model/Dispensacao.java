package com.integracao.farmacia_hospital.model;
import jakarta.persistence.*;

@Entity
public class Dispensacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataDispensacao;

    @ManyToOne
    private Receita receita;

    @ManyToOne
    private Farmacia farmacia;

    private String farmacêuticoResponsavel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataDispensacao() {
        return dataDispensacao;
    }

    public void setDataDispensacao(String dataDispensacao) {
        this.dataDispensacao = dataDispensacao;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public String getFarmacêuticoResponsavel() {
        return farmacêuticoResponsavel;
    }

    public void setFarmacêuticoResponsavel(String farmacêuticoResponsavel) {
        this.farmacêuticoResponsavel = farmacêuticoResponsavel;
    }
}