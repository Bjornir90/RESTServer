package com.ananas;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.util.Scanner;

public class Server {

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

        System.out.println("Saisir car+return pour stopper le serveur");
        System.out.println("Lancer un navigateur sur l'URL:" + baseURL+"image/index.html");
        Scanner sc = new Scanner(System.in);
        sc.next();

        System.out.println("Serveur stoppé !");

    }

}
