package com.pledge;

import com.pledge.models.Pledge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by grantheywood on 18/06/2016.
 */
@Path("/pledge/")
public class PledgeResource
{


    @Path("{pledgeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Pledge getPledge(@PathParam("pledgeId") int pledgeId)
    {
        Pledge testPledge = new Pledge();
        testPledge.title = "Blahhh";
        testPledge.delete_flag = 1;
        testPledge.description = "Blaaaaah";
        testPledge.type_id = 2;
        testPledge.user_id = 1;

        return testPledge;

    }

}
