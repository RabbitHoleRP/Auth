package br.com.rabbithole.auth.commands.user;

import br.com.rabbithole.auth.Auth;
import br.com.rabbithole.auth.data.sql.methods.AuthMethods;
import br.com.rabbithole.auth.entities.LoginProcessEntity;
import br.com.rabbithole.auth.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginCommand implements CommandExecutor {

    public LoginCommand() {
        PluginCommand command = Objects.requireNonNull(Bukkit.getPluginCommand("login"));
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtils.formatString("<red>Apenas jogadores podem executar este Comando!"));
            return true;
        }

        LoginProcessEntity loginProcess = Auth.getLoginProcessStorage().getInformation(player.getName());

        if (loginProcess == null) {
            player.sendMessage(StringUtils.formatString("<red>Você já está Logado!"));
            return true;
        }

        if (loginProcess.isLogged()) {
            player.sendMessage(StringUtils.formatString("<red>Você já está Autenticado!"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(StringUtils.formatString("<red>Utilize: /login <senha>."));
            return true;
        }

        String password = args[0];

        if (!AuthMethods.validateAccount(player.getName(), password)) {
            loginProcess.setAttempts(loginProcess.getAttempts() - 1);
            player.sendMessage(StringUtils.formatString("<red>Senha incorreta, você tem %o tentativas restantes!".formatted(loginProcess.getAttempts())));
            return true;
        }

        if (loginProcess.getAttempts() < 0) {
            player.kick(StringUtils.formatString("<red>Ultrapssou o Limite de Tentativas!"), PlayerKickEvent.Cause.TIMEOUT);
            return true;
        }

        player.sendMessage(StringUtils.formatString("<green>Autenticado com Sucesso, Seja Bem-Vindo!"));

        loginProcess.setLogged(true);
        Auth.getLoginProcessStorage().removeItemFromStorage(player.getName());
        return false;
    }
}
