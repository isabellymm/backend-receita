package com.integracao.farmacia_hospital.controller;

import com.integracao.farmacia_hospital.model.Medico;
import com.integracao.farmacia_hospital.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/registrar")
    public Medico registrarMedico(@RequestBody Medico medico) {
        return medicoService.salvarMedico(medico);
    }

}
