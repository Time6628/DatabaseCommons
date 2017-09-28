package me.time6628.commons.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class DynamoDBConfig {

    @Setting("Region")
    public String region = "us-east-1";

    public AmazonDynamoDB getDynamoDB() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        builder.setRegion(region);
        builder.setCredentials(new DefaultAWSCredentialsProviderChain());
        return builder.build();
    }

    public AmazonDynamoDBAsync getDynamoDBAsync() {
        AmazonDynamoDBAsyncClientBuilder builder = AmazonDynamoDBAsyncClientBuilder.standard();
        builder.setCredentials(new DefaultAWSCredentialsProviderChain());
        builder.setRegion(region);
        return builder.build();
    }
}
