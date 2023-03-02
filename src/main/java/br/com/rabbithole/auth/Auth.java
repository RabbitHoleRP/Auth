package br.com.rabbithole.auth;

import br.com.rabbithole.WarnUtils;
import br.com.rabbithole.auth.commands.user.ChangePasswordCommand;
import br.com.rabbithole.auth.commands.user.LoginCommand;
import br.com.rabbithole.auth.commands.user.RegisterCommand;
import br.com.rabbithole.auth.configuration.RedisConfiguration;
import br.com.rabbithole.auth.configuration.WormConfiguration;
import br.com.rabbithole.auth.data.cache.SessionProcessMethods;
import br.com.rabbithole.auth.data.storage.LoginProcessStorage;
import br.com.rabbithole.auth.events.JoinEvent;
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
    private static final WarnUtils warn = new WarnUtils("Auth");


    @Override
    public void onEnable() {
        // Plugin startup logic
        getWarn().sendWarn("<green>[Auth] iniciado com Sucesso!");
        registers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getWarn().sendWarn("<red>[Auth] desativado com Sucesso!");
        HandlerList.unregisterAll(this);
    }

    void registers() {
        saveDefaultConfig();
        commands();
        events();
        loginProcessStorage = new LoginProcessStorage();
        sessionMethods = new SessionProcessMethods();
        WormConfiguration.init(this);
        RedisConfiguration.init(this);
    }

    void commands() {
        new RegisterCommand();
        new LoginCommand();
        new ChangePasswordCommand();
    }

    void events() {
        new PreLoginEvent(this);
        new JoinEvent(this);
    }

    public static AuthAPI getAPI() {
        return API;
    }

    public static PermissionsAPI getPermissionsAPI() {
        return Permissions.getAPI();
    }

    public static WarnExecutor getWarn() {
        return warn.getWarn();
    }

    public static LoginProcessStorage getLoginProcessStorage() {
        return loginProcessStorage;
    }

    public static SessionProcessMethods getSessionMethods() {
        return sessionMethods;
    }

    public static Auth getInstance() {
        return Auth.getPlugin(Auth.class);
    }
}
