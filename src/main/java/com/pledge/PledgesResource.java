package com.pledge;

import com.google.common.collect.Lists;
import com.pledge.models.Pledge;
import com.pledge.mongo.MongoPledge;
import org.apache.log4j.Logger;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by grantheywood on 19/06/2016.
 */
@Path("/pledges/")
public class PledgesResource
{
    final static Logger logger = Logger.getLogger(PledgesResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pledge> getAllPledges()
    {
        logger.info("request to get all pledges");
        return MongoPledge.getAllPledges();
    }

    @GET
    @Path("populatetest")
    public Response populateTestPledges()
    {
        logger.info("request to populate Test Pledges");
        MongoPledge.populateTestPledges();
        return Response.ok().build();
    }

    @GET
    @Path("deletetest")
    public Response deleteTestPledges()
    {
        logger.info("request to delete test pledges");
        MongoPledge.removeAllPledges();
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPledge(Pledge pledge)
    {
        logger.info("adding pledge " + pledge);
        boolean result = MongoPledge.insertPledge(pledge);
        if (result)
            return Response.ok().build();
        else
            return Response.serverError().build();
    }
}
