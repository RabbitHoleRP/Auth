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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChangePasswordCommand implements CommandExecutor {

    public ChangePasswordCommand() {
        PluginCommand command = Objects.requireNonNull(Bukkit.getPluginCommand("changepassword"));
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringUtils.formatString("<red>Apenas jogadores podem executar este Comando!"));
            return true;
        }

        LoginProcessEntity loginProcess = Auth.getLoginProcessStorage().getInformation(player.getName());

        if (loginProcess != null) {
            String commandMessage = (loginProcess.isRegistered())  ? "<red>Você precisa estar Autenticado para executar este Comando!" : "<red>Você precisa estar Registrado para executar este Comando!";
            player.sendMessage(StringUtils.formatString(commandMessage));
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(StringUtils.formatString("<red>Utilize: /trocarsenha <Senha Atual> <Nova Senha>"));
            return true;
        }

        String password = args[0];
        String newPassword = args[1];

        if (!AuthMethods.validateAccount(player.getName(), password)) {
            player.sendMessage(StringUtils.formatString("<red>Sua Senha Atual está Incorreta!"));
            return true;
        }

        if (!AuthMethods.updatePassword(player.getName(), newPassword)) {
            player.sendMessage(StringUtils.formatString("<red>Erro ao atualizar sua Senha! Tente novamente mais Tarde!"));
            return true;
        }

        player.sendMessage(StringUtils.formatString("<green>Senha atualizada com Sucesso!"));
        return false;
    }
}
