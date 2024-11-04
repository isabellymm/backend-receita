package com.integracao.farmacia_hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssinaturaConfig {

    @Value("${assinatura.alias}")
    private String alias;

    @Value("${assinatura.senhaKeystore}")
    private String senhaKeystore;

    @Value("${assinatura.caminhoKeystore}")
    private String caminhoKeystore;

    public String getAlias() {
        return alias;
    }

    public String getSenhaKeystore() {
        return senhaKeystore;
    }

    public String getCaminhoKeystore() {
        return caminhoKeystore;
    }
}
