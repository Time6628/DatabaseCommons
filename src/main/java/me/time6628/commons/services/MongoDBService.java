package me.time6628.commons.services;

import com.mongodb.MongoClient;
import me.time6628.commons.config.MongoConfig;

public class MongoDBService {

    private MongoConfig config;

    public MongoDBService(MongoConfig config) {
        this.config = config;
    }

    public void setConfig(MongoConfig config) {
        this.config = config;
    }

    public MongoConfig getConfig() {
        return config;
    }

    /**
     * The MongoClient pools by itself, storing one and calling
     * upon it should work fine.
     * @return The MongoClient configured in the DataBaseCommons config.
     */
    public MongoClient getClient() {
        return config.getClient();
    }
}
