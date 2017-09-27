package me.time6628.commons.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Collections;

@ConfigSerializable
public class MongoConfig {
    @Setting("Host")
    public String host = "localhost";

    @Setting("Port")
    public int port = 27017;

    @Setting("User")
    public String username = "admin";

    @Setting("Password")
    public String password = "";

    @Setting("DataBase")
    public String database = "database";

    public MongoClient getClient() {
        MongoCredential cred =  MongoCredential.createCredential(username, database, password.toCharArray());
        return new MongoClient(new ServerAddress(host, port), Collections.singletonList(cred));
    }
}
