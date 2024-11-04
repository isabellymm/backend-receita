package com.integracao.farmacia_hospital.service;

import com.integracao.farmacia_hospital.model.Receita;
import com.integracao.farmacia_hospital.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita enviarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Receita buscarReceitaPorAssinaturaDigital(String assinaturaDigital) {
        return receitaRepository.findByAssinaturaDigital(assinaturaDigital);
    }

    public Optional<Receita> buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }
}