package me.time6628.commons;

import com.google.inject.Inject;
import me.time6628.commons.config.ConfigLoader;
import me.time6628.commons.services.DynamoDBService;
import me.time6628.commons.services.MongoDBService;
import me.time6628.commons.services.RedisService;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.io.File;

@Plugin(
        id = "databasecommons",
        name = "DatabaseCommons",
        description = "Common code and libraries for databases.",
        url = "https://kookykraftmc.net",
        authors = {
                "TimeTheCat"
        }
)
public class DatabaseCommons {

    @Inject
    @ConfigDir(sharedRoot = false)
    File configDir;
    
    @Inject
    GuiceObjectMapperFactory factory;

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        getLogger().info("Setting up config...");
        ConfigLoader cfgLoader = new ConfigLoader(this);
        if (!cfgLoader.loadRedis())
            game.getServiceManager().setProvider(this, RedisService.class, new RedisService(cfgLoader.getRedisConfig()));
        if (!cfgLoader.loadMongo())
            game.getServiceManager().setProvider(this, MongoDBService.class, new MongoDBService(cfgLoader.getMongoConfig()));
        if (!cfgLoader.loadDynamo())
            game.getServiceManager().setProvider(this, DynamoDBService.class, new DynamoDBService(cfgLoader.getDynamoDBConfig()));
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("DatabaseCommons loaded.");
    }

    public GuiceObjectMapperFactory getFactory() {
        return factory;
    }

    public File getConfigDir() {
        return configDir;
    }

    public Logger getLogger() {
        return logger;
    }
}
