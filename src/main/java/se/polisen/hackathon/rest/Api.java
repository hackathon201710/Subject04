package se.polisen.hackathon.rest;

import se.polisen.hackathon.dto.KeyValueDto;
import se.polisen.hackathon.entity.DomarEntity;
import se.polisen.hackathon.entity.TestEntity;
import se.polisen.hackathon.stateless.SearchBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
public class Api {

    @Inject
    private SearchBean searchBean;

    @GET
    @Produces("application/json; charset=UTF-8")
    public Response test(@QueryParam("keyword") String keyword) {
        List<TestEntity> result = searchBean.searchInfoByKeyword(keyword);
        List<KeyValueDto> copy = new ArrayList<>();
        result.forEach(entity -> copy.add(new KeyValueDto(entity.getId(), entity.getInfo())));
        return Response.ok(copy).build();
    }

    @GET
    @Path("/domar")
    @Produces("application/json; charset=UTF-8")
    public Response searchDomar(@QueryParam("keyword") String keyword, @QueryParam("phrase") String phrase) {
        List<DomarEntity> result = new ArrayList<>();
        if(keyword != null) {
            result.addAll(searchBean.searchDomarByKeyword(keyword));
        }
        if(phrase != null) {
            result.addAll(searchBean.searchDomarByPhrase(phrase));
        }

        List<KeyValueDto> copy = new ArrayList<>();
        result.forEach(entity -> copy.add(new KeyValueDto(entity.getId(), entity.getTitle())));
        return Response.ok(copy).build();
    }


}
