package com.integracao.farmacia_hospital.controller;

import com.integracao.farmacia_hospital.model.Medico;
import com.integracao.farmacia_hospital.model.Paciente;
import com.integracao.farmacia_hospital.model.Receita;
import com.integracao.farmacia_hospital.model.Medicamento;
import com.integracao.farmacia_hospital.service.AssinaturaDigitalService;
import com.integracao.farmacia_hospital.service.MedicoService;
import com.integracao.farmacia_hospital.service.PacienteService;
import com.integracao.farmacia_hospital.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private AssinaturaDigitalService assinaturaDigitalService;

    @PostMapping("/enviar")
    public Receita enviarReceita(@RequestBody Receita receita) {
        try {
            // Obter o médico autenticado
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Medico medico = medicoService.buscarPorUsuario(username);
            receita.setMedico(medico);

            // Definir a data de envio
            receita.setDataEnvio(LocalDate.now());

            // Salvar ou atualizar o paciente
            Paciente paciente = receita.getPaciente();
            if (paciente.getId() == null) {
                paciente = pacienteService.salvarPaciente(paciente);
                receita.setPaciente(paciente);
            } else {
                paciente = pacienteService.buscarPacientePorId(paciente.getId())
                        .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
                receita.setPaciente(paciente);
            }

            // Associar os medicamentos à receita
            List<Medicamento> medicamentos = receita.getMedicamentos();
            if (medicamentos != null) {
                for (Medicamento medicamento : medicamentos) {
                    medicamento.setReceita(receita);
                }
            }

            // Gerar a assinatura digital
            String dados = receita.dadosParaAssinatura();
            String assinatura = assinaturaDigitalService.assinarDados(dados);
            receita.setAssinaturaDigital(assinatura);

            // Salvar a receita (os medicamentos serão salvos devido ao cascade)
            return receitaService.salvarReceita(receita);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public Receita buscarReceita(@PathVariable Long id) {
        Optional<Receita> receita = receitaService.buscarReceitaPorId(id);
        return receita.orElse(null);
    }
}
