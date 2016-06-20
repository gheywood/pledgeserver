package com.pledge.mongo;

import com.google.common.collect.Lists;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.pledge.models.Pledge;
import com.pledge.mongo.adaptors.PledgeAdaptor;
import com.sun.research.ws.wadl.Doc;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by grantheywood on 19/06/2016.
 */
public class MongoPledge
{
    final static Logger logger = Logger.getLogger(MongoPledge.class.getName());
    private static final String pledgeCollectionName = "PledgeCollection";

    /*
        Class to handle all the Pledge related instances in Mongo
     */

    public static Pledge getPledgeForId(int id)
    {
        logger.info("getting pledge with id " + id);
        // Returns an Pledge object based on Id, or returns null if none exist
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> pledgeColl = database.getCollection(pledgeCollectionName);
        Document obj = pledgeColl.find(eq(PledgeAdaptor.ID,id)).first();
        Pledge p = null;

        if (obj != null)
        {
            p = PledgeAdaptor.toPledge(obj);
        }

        if (p == null)
        {
            logger.info("no pledge found for " + id);
        }

        return p;
    }

    public static List<Pledge> getPledgesForUserId(int userId)
    {
        logger.info("getting pledges that match user id " + userId);
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> col = database.getCollection(pledgeCollectionName);
        MongoCursor<Document> find = col.find(eq(PledgeAdaptor.USER_ID, userId)).iterator();
        List<Pledge> pledges = Lists.newArrayList();
        while(find.hasNext())
            pledges.add(PledgeAdaptor.toPledge(find.next()));
        return pledges;
    }

    public static List<Pledge> getAllPledges()
    {
        logger.info("getting all pledges");
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> pledgeColl = database.getCollection(pledgeCollectionName);

        MongoCursor<Document> find = pledgeColl.find().iterator();
        List<Pledge> pledges = Lists.newArrayList();
        while(find.hasNext())
        {
            Document o = find.next();
            pledges.add(PledgeAdaptor.toPledge(o));
        }
        find.close();

        logger.info("returning " + pledges.size() + " pledges");
        return pledges;
    }


    public static boolean insertPledge(Pledge p)
    {
        logger.info("inserting pledge " + p);
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> pledgeCol = database.getCollection(pledgeCollectionName);

        Document existing = pledgeCol.find(eq(PledgeAdaptor.ID,p.pledge_id)).first();
        if(existing != null)
        {
            // Updating
            logger.info(" Pledge " + p.pledge_id + " already exists, updating existing");
            pledgeCol.deleteOne(eq(PledgeAdaptor.ID,p.pledge_id));
            pledgeCol.insertOne(PledgeAdaptor.toMongo(p));
        }
        else
        {
            // create new
            pledgeCol.insertOne(PledgeAdaptor.toMongo(p));
        }

        return true;
    }


    public static void populateTestPledges()
    {
        logger.info("populating test pledges");
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> pledgeColl = database.getCollection(pledgeCollectionName);

        Pledge testPledge = new Pledge();
        testPledge.title = "Pledge1";
        testPledge.delete_flag = 1;
        testPledge.description = "Pledge 1";
        testPledge.type_id = 2;
        testPledge.user_id = 1;
        testPledge.pledge_id = 100;

        Pledge testPledge2 = new Pledge();
        testPledge2.title = "Pledge2";
        testPledge2.delete_flag = 1;
        testPledge2.description = "Pledge 2";
        testPledge2.type_id = 2;
        testPledge2.user_id = 2;
        testPledge2.pledge_id = 101;

        Pledge testPledge3 = new Pledge();
        testPledge3.title = "Pledge3";
        testPledge3.delete_flag = 1;
        testPledge3.description = "Pledge 3";
        testPledge3.type_id = 2;
        testPledge3.user_id = 3;
        testPledge3.pledge_id = 103;

        Pledge testPledge4 = new Pledge();
        testPledge4.title = "Pledge4";
        testPledge4.delete_flag = 1;
        testPledge4.description = "Pledge 4";
        testPledge4.type_id = 2;
        testPledge4.user_id = 4;
        testPledge4.pledge_id = 104;

        pledgeColl.insertOne(PledgeAdaptor.toMongo(testPledge));
        pledgeColl.insertOne(PledgeAdaptor.toMongo(testPledge2));
        pledgeColl.insertOne(PledgeAdaptor.toMongo(testPledge3));
        pledgeColl.insertOne(PledgeAdaptor.toMongo(testPledge4));
    }

    public static void removeAllPledges()
    {
        logger.info("removing all pledges");
        MongoDatabase database = MongoConnector.getDatabase();
        MongoCollection<Document> pledgeColl = database.getCollection(pledgeCollectionName);

        pledgeColl.deleteMany(eq(PledgeAdaptor.TYPE_ID,2));
    }


}
