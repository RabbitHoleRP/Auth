package br.com.rabbithole.auth.utils;

import br.com.rabbithole.auth.enums.Warn;
import org.bukkit.Bukkit;

public class WarnUtils {
    public static void sendWarn(Warn warn) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatString(warn.getMessage()));
    }

    public static void sendWarn(String msg) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.formatString(msg));
    }
}
