package br.com.rabbithole.auth.utils;

import br.com.rabbithole.auth.Auth;
import br.com.rabbithole.auth.configuration.RedisConfiguration;
import br.com.rabbithole.core.enums.Warn;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class RedisUtils {
    public static boolean setQuery(String key, String value) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.set(key, value);
            return true;
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean delQuery(String key) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.del(key);
            return true;
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            return false;
        }
    }

    public static boolean hashSetQuery(String key, String field, String value) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.hset(key, field, value);
            return true;
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean hashDelQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            jedis.hdel(key, field);
            return true;
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean hashExistsQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            return jedis.hexists(key, field);
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    public static Optional<String> hashGetQuery(String key, String field) {
        try (Jedis jedis = RedisConfiguration.getJedis().getResource()) {
            return Optional.of(jedis.hget(key, field));
        } catch (Exception exception) {
            Auth.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
