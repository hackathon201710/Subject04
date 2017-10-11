package se.polisen.hackathon.rest;

import se.polisen.hackathon.entity.TestEntity;
import se.polisen.hackathon.stateless.SearchBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
public class Api {

    @Inject
    private SearchBean searchBean;

    @GET
    @Produces("application/json")
    public Response test(@QueryParam("keyword") String keyword) {

        List<TestEntity> result = searchBean.findByInfo(keyword);

        return Response.ok(result).build();
    }


}
