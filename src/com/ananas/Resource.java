package com.ananas;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.awt.*;
import java.io.*;
import java.util.List;


public class Resource {

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public String sayHello(@QueryParam("name") String name){
        return "Hello " + name;
    }

    @GET
    @Produces("image/jpg")
    @Path("/image")
    public InputStream getImage(@QueryParam("name") String name) throws IOException {
        return new FileInputStream("./resources/" + name);
    }

    @GET
    @Produces("image/jpg")
    @Path("/image")
    public InputStream getImageFromIndex(@QueryParam("index") int index) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<ImageProperties> ImageList = objectMapper.readValue(new File("./resources/list.json"), typeFactory.constructCollectionType(List.class, ImageProperties.class));
        return getImage("./resources/" + ImageList.get(index).getName());
    }

    @GET
    @Produces("text/html")
    @Path("/form")
    public InputStream getData() throws IOException {
        return new FileInputStream("./resources/form.html");
    }

    @GET
    @Path("/response")
    public void receiveAnswer(@QueryParam("value") String resourceName) {
        System.out.println("resourceName = " + resourceName);
    }

}
