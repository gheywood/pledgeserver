package com.pledge;

import com.google.common.collect.Lists;
import com.pledge.models.Pledge;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by grantheywood on 19/06/2016.
 */
@Path("/pledges/")
public class PledgesResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pledge> getAllPledges()
    {
        Pledge testPledge = new Pledge();
        testPledge.title = "Pledge1";
        testPledge.delete_flag = 1;
        testPledge.description = "Pledge 1";
        testPledge.type_id = 2;
        testPledge.user_id = 1;

        Pledge testPledge2 = new Pledge();
        testPledge2.title = "Pledge2";
        testPledge2.delete_flag = 1;
        testPledge2.description = "Pledge 2";
        testPledge2.type_id = 2;
        testPledge2.user_id = 2;

        Pledge testPledge3 = new Pledge();
        testPledge3.title = "Pledge3";
        testPledge3.delete_flag = 1;
        testPledge3.description = "Pledge 3";
        testPledge3.type_id = 2;
        testPledge3.user_id = 3;

        Pledge testPledge4 = new Pledge();
        testPledge4.title = "Pledge4";
        testPledge4.delete_flag = 1;
        testPledge4.description = "Pledge 4";
        testPledge4.type_id = 2;
        testPledge4.user_id = 4;

        List<Pledge> pledges = Lists.newArrayList(testPledge, testPledge2, testPledge3, testPledge4);
        return pledges;
    }
}
