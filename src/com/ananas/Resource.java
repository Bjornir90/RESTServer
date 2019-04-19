package com.ananas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jdk.internal.util.xml.impl.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.internal.util.xml.impl.Input;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        return new FileInputStream("./resources/images/" + name);
    }

    @GET
    @Produces("image/jpg")
    @Path("/imageindex")
    public InputStream getImageFromIndex(@QueryParam("index") int index) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<ImageProperties> ImageList = objectMapper.readValue(new File("./resources/list.json"), typeFactory.constructCollectionType(List.class, ImageProperties.class));
        return getImage(ImageList.get(index).getFilename());
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

    @GET
    @Produces("application/json")
    @Path("/requestlist")
    public InputStream sendImageList() throws FileNotFoundException {
        return new FileInputStream("./resources/list.json");
    }
}
