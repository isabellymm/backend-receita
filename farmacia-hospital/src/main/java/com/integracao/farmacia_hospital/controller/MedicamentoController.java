package com.integracao.farmacia_hospital.controller;
import com.integracao.farmacia_hospital.model.Medicamento;
import com.integracao.farmacia_hospital.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // Endpoint para salvar um novo medicamento
    @PostMapping("/salvar")
    public Medicamento salvarMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoService.salvarMedicamento(medicamento);
    }

    // Endpoint para buscar um medicamento por ID
    @GetMapping("/{id}")
    public Medicamento buscarMedicamentoPorId(@PathVariable Long id) {
        Optional<Medicamento> medicamento = medicamentoService.buscarMedicamentoPorId(id);
        return medicamento.orElse(null);  // Retorna null se não encontrar, mas você pode adicionar um tratamento de erro adequado
    }

    // Endpoint para listar todos os medicamentos
    @GetMapping("/todos")
    public List<Medicamento> listarTodosMedicamentos() {
        return medicamentoService.listarTodosMedicamentos();
    }

    // Endpoint para deletar um medicamento por ID
    @DeleteMapping("/deletar/{id}")
    public void deletarMedicamento(@PathVariable Long id) {
        medicamentoService.deletarMedicamento(id);
    }

    // Endpoint para buscar medicamentos por nome
    @GetMapping("/nome/{nome}")
    public List<Medicamento> buscarMedicamentoPorNome(@PathVariable String nome) {
        return medicamentoService.buscarMedicamentoPorNome(nome);
    }
}
