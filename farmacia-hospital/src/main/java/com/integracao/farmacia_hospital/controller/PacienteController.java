package com.integracao.farmacia_hospital.controller;
import com.integracao.farmacia_hospital.model.Paciente;
import com.integracao.farmacia_hospital.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Endpoint para salvar um novo paciente
    @PostMapping("/salvar")
    public Paciente salvarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.salvarPaciente(paciente);
    }

    // Endpoint para buscar um paciente por ID
    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPacientePorId(id);
        return paciente.orElse(null);  // Retorna null se não encontrar, mas você pode adicionar um tratamento de erro adequado
    }

    // Endpoint para listar todos os pacientes
    @GetMapping("/todos")
    public List<Paciente> listarTodosPacientes() {
        return pacienteService.listarTodosPacientes();
    }

    // Endpoint para deletar um paciente por ID
    @DeleteMapping("/deletar/{id}")
    public void deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }

    // Endpoint para buscar um paciente por CPF
    @GetMapping("/cpf/{cpf}")
    public Paciente buscarPacientePorCpf(@PathVariable String cpf) {
        Optional<Paciente> paciente = pacienteService.buscarPacientePorCpf(cpf);
        return paciente.orElse(null);  // Retorna null se não encontrar, mas você pode adicionar um tratamento de erro adequado
    }
}
