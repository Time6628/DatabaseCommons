package me.time6628.commons.services;

import me.time6628.commons.config.DynamoDBConfig;

public class DynamoDBService {

    private DynamoDBConfig config;

    public DynamoDBService(DynamoDBConfig config) {
        this.config = config;
    }

    public DynamoDBConfig getConfig() {
        return config;
    }

    public void setConfig(DynamoDBConfig config) {
        this.config = config;
    }

}
