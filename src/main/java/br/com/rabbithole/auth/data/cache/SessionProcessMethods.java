package br.com.rabbithole.auth.data.cache;

import br.com.rabbithole.auth.utils.RedisUtils;

import java.util.Optional;

public class SessionProcessMethods {
    private final String KEY = "auth.sessions.";
    private final int SESSION_TIME = 300;

    public boolean createPlayerSession(String nick, String address) {
        boolean insertResult = RedisUtils.setQuery(KEY + nick, address);
        boolean expireResult = RedisUtils.expireQuery(KEY + nick, SESSION_TIME);
        return insertResult && expireResult;
    }

    public boolean checkPlayerSession(String nick, String address) {
        if (RedisUtils.existsQuery(KEY + nick)) {
            Optional<String> addressStored = RedisUtils.getQuery(KEY + nick);
            if (addressStored.isPresent()) {
                return address.equals(addressStored.get());
            }
        }
        return false;
    }

    public boolean forceClosePlayerSession(String nick) {
        return RedisUtils.delQuery(KEY + nick);
    }
}
