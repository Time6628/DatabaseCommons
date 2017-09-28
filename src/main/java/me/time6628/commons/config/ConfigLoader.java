package me.time6628.commons.config;

import com.google.common.reflect.TypeToken;
import me.time6628.commons.DatabaseCommons;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;

public class ConfigLoader {

    private final DatabaseCommons plugin;

    private RedisConfig redisConfig;
    private MongoConfig mongoConfig;
    private DynamoDBConfig dynamoDBConfig;

    public ConfigLoader(DatabaseCommons plugin) {
        this.plugin = plugin;
        if (!plugin.getConfigDir().exists())
            plugin.getConfigDir().mkdirs();
    }

    /////////////// REDIS ///////////////
    public boolean loadRedis() {
        try {
            File file = new File(plugin.getConfigDir(), "redis.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
            CommentedConfigurationNode config = loader.load(ConfigurationOptions.defaults().setObjectMapperFactory(plugin.getFactory()).setShouldCopyDefaults(true));
            redisConfig = config.getValue(TypeToken.of(RedisConfig.class), new RedisConfig());
            loader.save(config);
            return true;
        } catch (Exception e) {
            plugin.getLogger().error("Could not load config.", e);
            return false;
        }
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    /////////////// MongoDB ///////////////
    public boolean loadMongo() {
        try {
            File file = new File(plugin.getConfigDir(), "mongo.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
            CommentedConfigurationNode config = loader.load(ConfigurationOptions.defaults().setObjectMapperFactory(plugin.getFactory()).setShouldCopyDefaults(true));
            mongoConfig = config.getValue(TypeToken.of(MongoConfig.class), new MongoConfig());
            loader.save(config);
            return true;
        } catch (Exception e) {
            plugin.getLogger().error("Could not load config.", e);
            return false;
        }
    }

    public MongoConfig getMongoConfig() {
        return mongoConfig;
    }

    /////////////// DynamoDB ///////////////
    public boolean loadDynamo() {
        try {
            File file = new File(plugin.getConfigDir(), "dynamo.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
            CommentedConfigurationNode config = loader.load(ConfigurationOptions.defaults().setObjectMapperFactory(plugin.getFactory()).setShouldCopyDefaults(true));
            dynamoDBConfig = config.getValue(TypeToken.of(DynamoDBConfig.class), new DynamoDBConfig());
            loader.save(config);
            return true;
        } catch (Exception e) {
            plugin.getLogger().error("Could not load config.", e);
            return false;
        }
    }

    public DynamoDBConfig getDynamoDBConfig() {
        return dynamoDBConfig;
    }
}
