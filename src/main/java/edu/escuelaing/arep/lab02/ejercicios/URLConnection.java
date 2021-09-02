package edu.escuelaing.arep.lab02.ejercicios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLConnection {
    public  static void main(String[] args){
        datosURL("https://www.escuelaing.edu.co/es/programas/ingenieria-de-sistemas/");
        datosURL("https://www.grupobancolombia.com/personas/tarjetas-de-credito/american-express/libre");
        datosURL("https://www.amazon.com/-/es/Yesika-Salgado/dp/1945649135/ref=sr_1_2?__mk_es_US=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=corazon&qid=1631116664&sr=8-2");
    }

    public static void datosURL(String lineURL){
        try{
            URL siteUrl = new URL(lineURL);
            System.out.println(siteUrl);
            System.out.println("Protocol: "+siteUrl.getProtocol());
            System.out.println("Authority: "+siteUrl.getAuthority());
            System.out.println("Host: "+siteUrl.getHost());
            System.out.println("Port: "+siteUrl.getPort());
            System.out.println("Path: "+siteUrl.getPath());
            System.out.println("Query: "+siteUrl.getQuery());
            System.out.println("File: "+siteUrl.getFile());
            System.out.println("Ref: "+siteUrl.getRef());
            System.out.println("-----------------------------");
        }catch (MalformedURLException ex){
            Logger.getLogger(URLConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
