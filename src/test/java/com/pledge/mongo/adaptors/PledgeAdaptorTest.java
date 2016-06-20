package com.pledge.mongo.adaptors;

import com.pledge.models.Pledge;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by grantheywood on 20/06/2016.
 */
public class PledgeAdaptorTest
{
    private static int testId = 1;
    private static int testUserId = 1;
    private static String testTitle = "Test";
    private static String testDescription = "Test Desc";
    private static Date testStartDate = new Date(2016,06,06);
    private static Date testEndDate = new Date(2016,06,07);
    private static int testTypeId = 1;
    private static int testDeleteFlag = 1;

    @Test
    public void testConvertToPledge()
    {
        // Test that a mongo document is successfully converted to a pledge
        // test that all fields are intact
        Document doc = new Document(PledgeAdaptor.ID, testId)
                .append(PledgeAdaptor.USER_ID, testUserId)
                .append(PledgeAdaptor.TITLE, testTitle)
                .append(PledgeAdaptor.DESCRIPTION, testDescription)
                .append(PledgeAdaptor.START_DATE, testStartDate.toString())
                .append(PledgeAdaptor.END_DATE, testEndDate.toString())
                .append(PledgeAdaptor.TYPE_ID, testTypeId)
                .append(PledgeAdaptor.DELETE_FLAG, testDeleteFlag);

        Pledge ple = PledgeAdaptor.toPledge(doc);

        assertEquals(ple.pledge_id,doc.get(PledgeAdaptor.ID));
        assertEquals(ple.user_id,doc.get(PledgeAdaptor.USER_ID));
        assertEquals(ple.title,doc.get(PledgeAdaptor.TITLE));
        assertEquals(ple.description,doc.get(PledgeAdaptor.DESCRIPTION));
        //assertEquals(ple.start_date,doc.get(PledgeAdaptor.START_DATE));
        //assertEquals(ple.end_date,doc.get(PledgeAdaptor.END_DATE));
        assertEquals(ple.type_id,doc.get(PledgeAdaptor.TYPE_ID));
        assertEquals(ple.delete_flag,doc.get(PledgeAdaptor.DELETE_FLAG));
    }

    @Test
    public void testConvertToDocument()
    {
        Pledge ple = new Pledge();
        ple.pledge_id = testId;
        ple.user_id = testUserId;
        ple.title = testTitle;
        ple.description = testDescription;
        ple.start_date = testStartDate;
        ple.end_date = testEndDate;
        ple.type_id = testTypeId;
        ple.delete_flag = testDeleteFlag;

        Document doc = PledgeAdaptor.toMongo(ple);

        assertEquals(doc.get(PledgeAdaptor.ID),ple.pledge_id);
        assertEquals(doc.get(PledgeAdaptor.USER_ID),ple.user_id);
        assertEquals(doc.get(PledgeAdaptor.TITLE),ple.title);
        assertEquals(doc.get(PledgeAdaptor.DESCRIPTION),ple.description);
        //assertEquals(doc.get(PledgeAdaptor.START_DATE),ple.start_date);
        //assertEquals(doc.get(PledgeAdaptor.END_DATE),ple.end_date);
        assertEquals(doc.get(PledgeAdaptor.TYPE_ID),ple.type_id);
        assertEquals(doc.get(PledgeAdaptor.DELETE_FLAG),ple.delete_flag);
    }
}
