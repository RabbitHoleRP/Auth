package br.com.rabbithole.auth.data.cache;

import br.com.rabbithole.auth.utils.RedisUtils;

public class SessionProcessMethods {
    private final String KEY = "auth.sessions.";
    private final int SESSION_TIME = 300;

    public boolean createPlayerSession(String nick) {
        boolean insertResult = RedisUtils.setQuery(KEY + nick, nick);
        boolean expireResult = RedisUtils.expireQuery(KEY + nick, SESSION_TIME);
        return insertResult && expireResult;
    }

    public boolean checkPlayerSession(String nick) {
        return RedisUtils.existsQuery(KEY + nick);
    }

    public boolean forceClosePlayerSession(String nick) {
        return RedisUtils.delQuery(KEY + nick);
    }
}
