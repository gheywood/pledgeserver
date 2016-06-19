package com.pledge.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;


import java.net.UnknownHostException;

/**
 * Created by grantheywood on 19/06/2016.
 */
public class MongoConnector
{
    final static Logger logger = Logger.getLogger(MongoConnector.class);
    private static MongoClient mongoClient;
    public static final String mongoDbURL = "mongodb://pledgeadmin:pledge@waffle.modulusmongo.net:27017/si5xEgip";
    private static final String mongoUser = "pledgeadmin";
    private static final String mongoPassword = "pledge";
    private static boolean mongoConnected = false;
    public static final String mongoDatabase = "si5xEgip";

    private MongoConnector() {}

    public static MongoClient getMongoClient()
    {
        if (mongoConnected == false) {
            logger.info("Connecting to MongoDb " + mongoDbURL);
            mongoClient = new MongoClient(new MongoClientURI(mongoDbURL));
            mongoConnected = true;
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase()
    {
        return getMongoClient().getDatabase(mongoDatabase);
    }
}
