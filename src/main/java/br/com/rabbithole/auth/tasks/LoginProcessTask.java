package br.com.rabbithole.auth.tasks;

import br.com.rabbithole.auth.Auth;
import br.com.rabbithole.auth.entities.LoginProcessEntity;
import br.com.rabbithole.auth.utils.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class LoginProcessTask {
    public static void init(Player player) {
        LoginProcessEntity loginProcess = Auth.getLoginProcessStorage().getInformation(player.getName());

        new BukkitRunnable() {
            private int time = 60;

            @Override
            public void run() {
                //player.sendActionBar(StringUtils.formatString("<red>Você tem %o Segundos!".formatted(this.rogerio))); //TODO: CORRIGIR DPS BUG TEMPO -3
                this.time = time -1;

                if (!player.isOnline() || loginProcess.isLogged() || this.time == 0) {
                    cancel();
                    Auth.getLoginProcessStorage().removeItemFromStorage(player.getName());
                    return;
                }

                if (loginProcess.getAttempts() == 0) {
                    cancel();
                    Auth.getLoginProcessStorage().removeItemFromStorage(player.getName());
                    kick(player, "<red>Você ultrapassou o Limite de Tentativas!");
                }


                if (this.time == 0) {
                    cancel();
                    kick(player, "<red>Você demorou muito!");
                    Auth.getLoginProcessStorage().removeItemFromStorage(player.getName());
                }

                long announcementTime = this.time;
                if (announcementTime % 5 == 0) {
                    String commandMessage = (loginProcess.isRegistered()) ? "<green>Utilize: /login <senha>." : "<green>Utilize: /registrar <senha> <senha>.";
                    player.sendMessage(StringUtils.formatString(commandMessage));
                }
            }
        }.runTaskTimerAsynchronously(Auth.getInstance(), 0L, 20L);
    }

    private static void kick(Player player, String reason) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.kick(StringUtils.formatString(reason), PlayerKickEvent.Cause.TIMEOUT);
            }
        }.runTask(Auth.getInstance());
    }
}
