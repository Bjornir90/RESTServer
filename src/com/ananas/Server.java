package com.ananas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.io.*;
import java.util.Scanner;

public class Server {

    public static void updateImageList(){
        File imageFolder = new File("./resources/images");
        File[] listOfImages = imageFolder.listFiles();

        ObjectMapper om = new ObjectMapper();

        ArrayNode an = om.createArrayNode();

        for (int i = 0; i<listOfImages.length; i++){
            ObjectNode on = om.createObjectNode();
            on.put("filename", listOfImages[i].getName());
            on.put("size", listOfImages[i].length());
            on.put("index", i);
            an.add(on);
        }

        try {
            FileWriter listFile = new FileWriter("./resources/list.json");
            listFile.write(an.toString());
            listFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String baseURL = "http://localhost:9000/";
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setProvider(new JacksonJaxbJsonProvider());
        sf.setResourceClasses(Resource.class);
        sf.setResourceProvider(
                Resource.class,
                new SingletonResourceProvider(new Resource())
        );
        sf.setAddress(baseURL);
        sf.create();

        updateImageList();

        System.out.println("Saisir car+return pour stopper le serveur");
        System.out.println("Lancer un navigateur sur l'URL:" + baseURL + "hello");
        Scanner sc = new Scanner(System.in);
        sc.next();

        System.out.println("Serveur stoppé !");

    }

}
