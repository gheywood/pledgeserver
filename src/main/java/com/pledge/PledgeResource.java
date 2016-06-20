package com.pledge;

import com.pledge.models.Pledge;
import com.pledge.mongo.MongoPledge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by grantheywood on 18/06/2016.
 */
@Path("/pledge/")
public class PledgeResource
{
    final static Logger logger = Logger.getLogger(PledgeResource.class.getName());
    @Path("{pledgeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Pledge getPledge(@PathParam("pledgeId") int pledgeId)
    {
        logger.info("request to get pledge with id " + pledgeId);
        return MongoPledge.getPledgeForId(pledgeId);
    }
}
