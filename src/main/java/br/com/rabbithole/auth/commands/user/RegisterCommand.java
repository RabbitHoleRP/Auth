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

public class RegisterCommand implements CommandExecutor {

    public RegisterCommand() {
        PluginCommand command = Objects.requireNonNull(Bukkit.getPluginCommand("register"));
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
            player.sendMessage(StringUtils.formatString("<red>Você já está Registrado!"));
            return true;
        }

        if (loginProcess.isRegistered()) {
            player.sendMessage(StringUtils.formatString("<red>Você já está registrado! Utilize: /login <senha>."));
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(StringUtils.formatString("<red>Utilize: /registrar <senha> <senha>."));
            return true;
        }

        String password = args[0];
        String passwordConfirmation = args[1];

        if (!password.equals(passwordConfirmation)) {
            player.sendMessage(StringUtils.formatString("<red>Suas senhas são Diferentes!"));
            return true;
        }

        if (!AuthMethods.createAccount(player.getName(), player.getUniqueId(), password, loginProcess.getAddress().toString())) {
            player.kick(StringUtils.formatString("<red>Erro ao Registrar sua Conta!"), PlayerKickEvent.Cause.TIMEOUT);
            return true;
        }

        player.sendMessage(StringUtils.formatString("<green>Registrado com Sucesso, Seja Bem-Vindo!"));

        loginProcess.setRegistered(true);
        loginProcess.setLogged(true);
        Auth.getLoginProcessStorage().updateItemStoraged(player.getName(), loginProcess);
        return false;
    }
}
