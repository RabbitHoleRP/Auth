package br.com.rabbithole.auth.cryptography;

import br.com.rabbithole.auth.utils.StringUtils;
import com.google.common.hash.Hashing;
import org.bukkit.Bukkit;

import java.nio.charset.StandardCharsets;

public class SHA256 {

    /**
     *
     * @param input Entrada de valor que será encriptada em SHA256
     * @return String — Valor encriptado
     */
    public static String encrypt(String input) {
        return Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
    }

    /**
     *
     * @param passwordInput Entrada de senha digitada pelo usuário em OnJoin
     * @param passwordEncrypted Senha que foi recuperada no Banco de Dados
     * @return Boolean — Comparação das entradas de dados
     */
    public static boolean validatePassword(String passwordInput, String passwordEncrypted) {
        try {
            String inputEncrypted = encrypt(passwordInput);
            return inputEncrypted.equals(passwordEncrypted);
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.formatString("<red>[Auth] erro ao validar senha em Método: <SHA256.validatePassword>"));
            return false;
        }
    }
}
