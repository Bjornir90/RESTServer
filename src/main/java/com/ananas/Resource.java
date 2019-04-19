package com.ananas;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Resource {

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public String sayHello(@QueryParam("nom") String name){
        return "Hello " + name;
    }

    @GET
    @Produces("image/jpg")
    @Path("/image")
    public InputStream getImage() throws IOException {
        return new FileInputStream("./src/main/resources/image.jpg");
    }
}
