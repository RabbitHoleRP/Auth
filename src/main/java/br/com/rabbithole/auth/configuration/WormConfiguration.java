package br.com.rabbithole.auth.configuration;

import br.com.rabbithole.auth.data.sql.tables.AuthTable;
import dev.gump.worm.Worm;
import dev.gump.worm.WormConnection;
import org.bukkit.plugin.Plugin;

public class WormConfiguration {
    public static void init(Plugin plugin) {
        String host = plugin.getConfig().getString("MySQL.Host");
        int port = plugin.getConfig().getInt("MySQL.Port");
        String password = plugin.getConfig().getString("MySQL.Password");
        String user = plugin.getConfig().getString("MySQL.User");
        String database = plugin.getConfig().getString("MySQL.Database");

        Worm.getRegistry().registerTable("AuthTable", AuthTable.class);

        WormConnection connection = new WormConnection(host, port, user, password, database);
        Worm.init(connection);
    }
}
