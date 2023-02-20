package br.com.rabbithole.auth;

import br.com.rabbithole.WarnUtils;
import br.com.rabbithole.auth.data.cache.SessionProcessMethods;
import br.com.rabbithole.auth.data.storage.LoginProcessStorage;
import br.com.rabbithole.auth.events.PreLoginEvent;
import br.com.rabbithole.core.WarnExecutor;
import br.com.rabbithole.permissions.Permissions;
import br.com.rabbithole.permissions.PermissionsAPI;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Auth extends JavaPlugin {
    private static final AuthAPI API = new AuthAPI();
    private static LoginProcessStorage loginProcessStorage;
    private static SessionProcessMethods sessionMethods;


    @Override
    public void onEnable() {
        // Plugin startup logic
        WarnUtils.getWarn().sendWarn("<green>[Auth] iniciado com Sucesso!");
        registers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WarnUtils.getWarn().sendWarn("<red>[Auth] desativado com Sucesso!");
        HandlerList.unregisterAll(this);
    }

    void registers() {
        commands();
        events();
        loginProcessStorage = new LoginProcessStorage();
        sessionMethods = new SessionProcessMethods();
        WarnUtils.warnInitializer("Auth");
    }

    void commands() {}

    void events() {
        new PreLoginEvent(this);
    }

    public static AuthAPI getAPI() {
        return API;
    }

    public static PermissionsAPI getPermissionsAPI() {
        return Permissions.getAPI();
    }

    public static WarnExecutor getWarn() {
        return WarnUtils.getWarn();
    }

    public static LoginProcessStorage getLoginProcessStorage() {
        return loginProcessStorage;
    }

    public static SessionProcessMethods getSessionMethods() {
        return sessionMethods;
    }
}
