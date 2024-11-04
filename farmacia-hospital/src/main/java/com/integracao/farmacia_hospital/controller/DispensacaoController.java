package com.integracao.farmacia_hospital.controller;

import com.integracao.farmacia_hospital.model.Dispensacao;
import com.integracao.farmacia_hospital.service.DispensacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dispensacoes")
public class DispensacaoController {

    @Autowired
    private DispensacaoService dispensacaoService;

    // Endpoint para salvar uma nova dispensação
    @PostMapping("/salvar")
    public Dispensacao salvarDispensacao(@RequestBody Dispensacao dispensacao) {
        return dispensacaoService.salvarDispensacao(dispensacao);
    }

    // Endpoint para buscar uma dispensação por ID
    @GetMapping("/{id}")
    public Dispensacao buscarDispensacaoPorId(@PathVariable Long id) {
        Optional<Dispensacao> dispensacao = dispensacaoService.buscarDispensacaoPorId(id);
        return dispensacao.orElse(null);  // Retorna null se não encontrar, mas você pode adicionar um tratamento de erro adequado
    }

    // Endpoint para listar todas as dispensações
    @GetMapping("/todas")
    public List<Dispensacao> listarTodasDispensacoes() {
        return dispensacaoService.listarTodasDispensacoes();
    }

    // Endpoint para deletar uma dispensação por ID
    @DeleteMapping("/deletar/{id}")
    public void deletarDispensacao(@PathVariable Long id) {
        dispensacaoService.deletarDispensacao(id);
    }
}
