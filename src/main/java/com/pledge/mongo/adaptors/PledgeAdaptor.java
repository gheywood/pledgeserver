package com.pledge.mongo.adaptors;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pledge.models.Pledge;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by grantheywood on 19/06/2016.
 */
public class PledgeAdaptor
{
    /*
    List of fields in the mongo model, they should match up to the fields in the Pledge class
     */
    public static String ID = "_id";
    public static String USER_ID = "user_id";
    public static String TITLE = "title";
    public static String DESCRIPTION = "description";
    public static String START_DATE = "start_date";
    public static String END_DATE = "end_date";
    public static String TYPE_ID = "type_id";
    public static String DELETE_FLAG = "delete_flag";

    /*
    Method to convert from a Pledge to a Mongo Document
     */
    public static Document toMongo(Pledge p) {
        return new Document(ID, p.pledge_id)
                .append(USER_ID, p.user_id)
                .append(TITLE, p.title)
                .append(DESCRIPTION, p.description)
                .append(START_DATE, p.start_date)
                .append(END_DATE, p.end_date)
                .append(TYPE_ID, p.type_id)
                .append(DELETE_FLAG, p.delete_flag);
    }

    /*
    Method to convert from a Mongo Document to a Pledge
     */
    public static Pledge toPledge(Document mongoObj)
    {
        Pledge newPledge = new Pledge();
        newPledge.pledge_id = (int)mongoObj.get(ID);
        newPledge.user_id = (int)mongoObj.get(USER_ID);
        newPledge.title = (String)mongoObj.get(TITLE);
        newPledge.description = (String)mongoObj.get(DESCRIPTION);
        try {
            newPledge.start_date = new SimpleDateFormat("yyyyMMdd").parse(mongoObj.get(START_DATE).toString());
            newPledge.end_date = new SimpleDateFormat("yyyyMMdd").parse(mongoObj.get(END_DATE).toString());
        } catch (Exception e)
        {
            // nothing
        }
        newPledge.type_id = (int)mongoObj.get(TYPE_ID);
        newPledge.delete_flag = (int)mongoObj.get(DELETE_FLAG);
        return newPledge;
    }
}
