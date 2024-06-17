package com.connectMentor.auth.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String senha) {
        return encoder.encode(senha);
    }

    public static boolean matches(String senha, String encodedSenha) {
        return encoder.matches(senha, encodedSenha);
    }
}
