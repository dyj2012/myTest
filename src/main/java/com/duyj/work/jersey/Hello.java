package com.duyj.work.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by LG on 2017/9/21.
 */
@Path("/hello")
public class Hello {

    //访问路径 /hello
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world!";
    }

    //访问路径 /hello/text/name123
    @GET
    @Path("/text/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) throws Exception {
        return "hello, " + name;
    }

    //访问路径 /hello/json/name123
    @GET
    @Path("/json/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello2(@PathParam("name") String name) throws Exception {
        return "hello, " + name;
    }

    //访问路径 /hello/xml/name123
    @GET
    @Path("/xml/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public String hello3(@PathParam("name") String name) throws Exception {
        return "hello, " + name;
    }

}
