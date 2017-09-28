package me.time6628.commons.services;

import me.time6628.commons.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisService {

    private RedisConfig config;

    public RedisService(RedisConfig config) {
        this.config = config;
    }

    public void setConfig(RedisConfig config) {
        this.config = config;
    }

    public RedisConfig getConfig() {
        return config;
    }

    /**
     * @return Gets the pool of Jedis clients.
     */
    public JedisPool getPool() {
        return config.getJedisPool();
    }

    /**
     * @return A single Jedis instance from the pool.
     */
    public Jedis getJedis() {
        return config.getJedisPool().getResource();
    }
}
