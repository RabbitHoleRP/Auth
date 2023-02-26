package br.com.rabbithole.auth.events;

import br.com.rabbithole.auth.Auth;
import br.com.rabbithole.auth.entities.LoginProcessEntity;
import br.com.rabbithole.auth.tasks.LoginProcessTask;
import br.com.rabbithole.auth.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinEvent implements Listener {
    final Plugin plugin;

    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        LoginProcessEntity loginProcess = Auth.getLoginProcessStorage().getInformation(event.getPlayer().getName());
        if (loginProcess == null) {
            event.getPlayer().kick(StringUtils.formatString("<red>Erro de Segurança! Por favor entre Novamente!"));
            return;
        }

        LoginProcessTask.init(event.getPlayer());
    }
}
