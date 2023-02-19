package br.com.rabbithole.auth.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;


public class StringUtils {

    /**
     *
     * @param input String que será convertida para Component (Adventure)
     * @return Component — Component (Adventure) convertido
     */
    public static Component formatString(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }
}
