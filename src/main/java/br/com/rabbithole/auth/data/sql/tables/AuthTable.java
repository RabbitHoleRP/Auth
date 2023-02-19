package br.com.rabbithole.auth.data.sql.tables;

import dev.gump.worm.WormField;
import dev.gump.worm.WormTable;

public class AuthTable extends WormTable {
    @WormField(sqlName = "nick", sqlType = "VARCHAR", length = 16)
    private String nick;

    @WormField(sqlName = "uuid", sqlType = "VARCHAR", length = 32)
    private String uuid;

    @WormField(sqlName = "password", sqlType = "VARCHAR", length = 64)
    private String password;

    @WormField(sqlName = "firstIp", sqlType = "VARCHAR", length = 18)
    private String firstIp;

    @WormField(sqlName = "email", sqlType = "VARCHAR", length = 64, nullable = true, defaultValue = " NULL ")
    private String email;

    public AuthTable() {
    }

    public AuthTable(String nick) {
        this.nick = nick;
    }

    public AuthTable(String nick, String uuid, String password, String firstIp) {
        this.nick = nick;
        this.uuid = uuid;
        this.password = password;
        this.firstIp = firstIp;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstIp() {
        return firstIp;
    }

    public void setFirstIp(String firstIp) {
        this.firstIp = firstIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
