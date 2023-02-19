package br.com.rabbithole.auth;

import br.com.rabbithole.auth.data.storage.LoginProcessStorage;
import br.com.rabbithole.auth.events.PreLoginEvent;
import br.com.rabbithole.auth.utils.WarnUtils;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Auth extends JavaPlugin {
    private static final AuthAPI API = new AuthAPI();
    private static LoginProcessStorage loginProcessStorage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        WarnUtils.sendWarn("<green>[Auth] iniciado com Sucesso!");
        registers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WarnUtils.sendWarn("<red>[Auth] desativado com Sucesso!");
        HandlerList.unregisterAll(this);
    }

    void registers() {
        commands();
        events();
        loginProcessStorage = new LoginProcessStorage();
    }

    void commands() {}

    void events() {
        new PreLoginEvent(this);
    }

    public static AuthAPI getAPI() {
        return API;
    }

    public static LoginProcessStorage getLoginProcessStorage() {
        return loginProcessStorage;
    }
}
