package com.integracao.farmacia_hospital.service;

import com.integracao.farmacia_hospital.config.AssinaturaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

@Service
public class AssinaturaDigitalService {

    private static String senhaKeystore = null;
    private static String alias  = null;
    private static String caminhoKeystore = null;

    @Autowired
    public AssinaturaDigitalService(AssinaturaConfig assinaturaConfig) {
        this.alias = assinaturaConfig.getAlias();
        this.senhaKeystore = assinaturaConfig.getSenhaKeystore();
        this.caminhoKeystore = assinaturaConfig.getCaminhoKeystore();
    }

    public String assinarDados(String dados) throws Exception {
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new FileInputStream(caminhoKeystore), senhaKeystore.toCharArray());

        PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, senhaKeystore.toCharArray());
        System.out.println("privateKey" + privateKey );
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(dados.getBytes());
        byte[] assinatura = signature.sign();
        System.out.println("assinatura" + assinatura );

        return Base64.getEncoder().encodeToString(assinatura);
    }

    public static boolean verificarAssinatura(String dados, String assinaturaBase64) throws Exception {
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new FileInputStream(caminhoKeystore), senhaKeystore.toCharArray());

        Certificate cert = keystore.getCertificate(alias);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(cert);
        signature.update(dados.getBytes());

        byte[] assinatura = Base64.getDecoder().decode(assinaturaBase64);
        return signature.verify(assinatura);
    }
}
