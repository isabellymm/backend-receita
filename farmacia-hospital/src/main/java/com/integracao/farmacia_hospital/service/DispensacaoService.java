package com.integracao.farmacia_hospital.service;
import com.integracao.farmacia_hospital.model.Dispensacao;
import com.integracao.farmacia_hospital.repository.DispensacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispensacaoService {

    @Autowired
    private DispensacaoRepository dispensacaoRepository;

    // Método para salvar uma dispensação
    public Dispensacao salvarDispensacao(Dispensacao dispensacao) {
        return dispensacaoRepository.save(dispensacao);
    }

    // Método para buscar uma dispensação por ID
    public Optional<Dispensacao> buscarDispensacaoPorId(Long id) {
        return dispensacaoRepository.findById(id);
    }

    // Método para buscar todas as dispensações
    public List<Dispensacao> listarTodasDispensacoes() {
        return dispensacaoRepository.findAll();
    }

    // Método para deletar uma dispensação por ID
    public void deletarDispensacao(Long id) {
        dispensacaoRepository.deleteById(id);
    }
}
