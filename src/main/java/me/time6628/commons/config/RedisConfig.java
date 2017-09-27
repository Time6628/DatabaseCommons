package me.time6628.commons.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ConfigSerializable
public class RedisConfig {

    @Setting("Host")
    public String host = "localhost";

    @Setting("Port")
    public int port = 6379;

    @Setting("Password")
    public String password = "";

    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(128);
        if (!password.equals("")) {
            return new JedisPool(config, this.host, this.port, 0, this.password);
        } else {
            return new JedisPool(config, this.host, this.port, 0);
        }
    }
}
