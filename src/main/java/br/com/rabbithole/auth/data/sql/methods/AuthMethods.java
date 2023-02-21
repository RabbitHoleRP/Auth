package br.com.rabbithole.auth.data.sql.methods;

import br.com.rabbithole.auth.cryptography.SHA256;
import br.com.rabbithole.auth.data.sql.tables.AuthTable;

import java.util.UUID;

public class AuthMethods {
    public static boolean createAccount(String nick, UUID uuid, String password, String firstIp) {
        try (AuthTable account = new AuthTable(nick, uuid.toString(), SHA256.encrypt(password), firstIp)) {
            if (!account.find()) return account.insert();
        }
        return false;
    }

    public static boolean hasAccount(String nick) {
        try (AuthTable account = new AuthTable(nick)) {
            return account.find();
        }
    }

    public static boolean updatePassword(String nick, String password) {
        try (AuthTable account = new AuthTable(nick)) {
            if (account.find()) {
                account.setPassword(SHA256.encrypt(password));
                return account.update();
            }
        }
        return false;
    }

    public static boolean updateEmail(String nick, String email) {
        try (AuthTable account = new AuthTable(nick)) {
            if (account.find()) {
                account.setEmail(email);
                return account.update();
            }
        }
        return false;
    }

    public static boolean deleteAccount(String nick) {
        try (AuthTable account = new AuthTable(nick)) {
            if (account.find()) return account.delete();
        }
        return false;
    }

    //TODO: FOR FUTURE FEATURE
    public static boolean checkIp(String nick, String ip) {
        try (AuthTable account = new AuthTable(nick)) {
            if (account.find()) {
                return ip.equals(account.getFirstIp());
            }
        }
        return false;
    }
}
