package com.integracao.farmacia_hospital.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografiaService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encriptarSenha(String senha) {
        return encoder.encode(senha);
    }

    public static boolean verificarSenha(String senha, String senhaEncriptada) {
        return encoder.matches(senha, senhaEncriptada);
    }
}
