package com.integracao.farmacia_hospital.controller;
import com.integracao.farmacia_hospital.model.Farmacia;
import com.integracao.farmacia_hospital.model.Receita;
import com.integracao.farmacia_hospital.service.AssinaturaDigitalService;
import com.integracao.farmacia_hospital.service.FarmaciaService;
import com.integracao.farmacia_hospital.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    public FarmaciaController(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }

    @PostMapping("/registrar")
    public Farmacia registrarFarmacia(@RequestBody Farmacia farmacia) {
        return farmaciaService.salvarFarmacia(farmacia);
    }

    @GetMapping("/{id}")
    public Farmacia buscarFarmaciaPorId(@PathVariable Long id) {
        Optional<Farmacia> farmacia = farmaciaService.buscarFarmaciaPorId(id);
        return farmacia.orElse(null);  // Retorna null se não encontrar, mas você pode adicionar um tratamento de erro adequado
    }

    @GetMapping("/todas")
    public List<Farmacia> listarTodasFarmacias() {
        return farmaciaService.listarTodasFarmacias();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarFarmacia(@PathVariable Long id) {
        farmaciaService.deletarFarmacia(id);
    }

    @GetMapping("/cnpj/{cnpj}")
    public Optional<Farmacia> buscarFarmaciaPorCnpj(@PathVariable String cnpj) {
        Optional<Optional<Farmacia>> farmacia = farmaciaService.buscarFarmaciaPorCnpj(cnpj);
        return farmacia.orElse(null);
    }

    @GetMapping("/receber/{id}")
    public Receita receberReceita(@PathVariable Long id) {
        ReceitaService receitaService = new ReceitaService();
        Optional<Receita> receita = receitaService.buscarReceitaPorId(id);
        try {
            String dados = receita.toString();
            boolean assinaturaValida = AssinaturaDigitalService.verificarAssinatura(
                    dados,
                    receita.get().getAssinaturaDigital()
            );
            if (assinaturaValida) {
                // A assinatura é válida
                return receita.orElse(null);
            } else {
                // A assinatura não é válida
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
