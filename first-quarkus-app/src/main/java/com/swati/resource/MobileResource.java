package com.swati.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
    private static final Logger log = LoggerFactory.getLogger(MobileResource.class);
    List<String> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewMobile(String mobileName){
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{oldMobile}")
    public Response updateMobile(@PathParam("oldMobile") String oldName,@QueryParam("newMobile") String newName){
        mobileList=mobileList.stream().map(mobile ->{
            if (mobile.equals(oldName)){
             return newName;
            }else {
             return mobile;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }


    @DELETE
    @Path("/mobileToDelete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName){
        boolean isRemoved = mobileList.remove(mobileName);
        if (isRemoved){
            return Response.ok(mobileList).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
