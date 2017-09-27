package me.time6628.commons.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class DynamoDBConfig {

    @Setting("Region")
    public String region = "us-east-1";
}
