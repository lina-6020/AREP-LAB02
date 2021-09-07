package edu.escuelaing.arep.lab02;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
/**
 *
 * @author Lina Buitrago
 *
 */
public class HttpServer {

    public HttpServer() {
        super();
    }

    /**
     * Main method of the application
     * @param args
     */
    public static void main(String[] args) {
        HttpServer hserver = new HttpServer();
        try {
            hserver.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is usted to start the application client-server
     * @throws IOException
     */
    private void startServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean entry = false;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (inputLine.startsWith("GET")) {

                    String resource = inputLine.split(" ")[1];
                    if (resource.contains("html")) {
                        outResource(resource, out, "html");
                        entry = true;
                    } else if (resource.contains("js")) {
                        outResource(resource, out, "json");
                        entry = true;
                    } else if (resource.contains("png")) {
                        entry = true;
                        outResourceImage(resource, clientSocket.getOutputStream());
                    }

                }
                if (!in.ready()) {
                    break;
                }
            }

            if (!entry) {
                outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
                        + "<title>Title of the document</title>\n" + "</head>\n" + "<body>\n"
                        + "<h1>Mi propio mensaje</h1>\n" + "</body>\n" + "</html>\n";

                out.println(outputLine);
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * This method is used to send the resource(html or json) to client
     * @param resource Part of path of resource
     * @param out Is the socket to send
     * @param term String that is html or json
     */
    private void outResource(String resource, PrintWriter out, String term) {

        try {
            FileReader f = new FileReader(System.getProperty("user.dir") + "/src/resources" + resource);
            BufferedReader b = new BufferedReader(f);
            String text = "";
            String outText = "";
            while ((text = b.readLine()) != null) {
                outText += text;
            }
            b.close();
            out.println("HTTP/1.1 201 OK\r\n" + "Content-Type: text/" + term + ";" + "charset=\"UTF-8\" \r\n" + "\r\n"
                    + outText);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This method is used to send the image to the client
     * @param resource Part of path of resource
     * @param entrada Socket to send
     */
    private void outResourceImage(String resource, OutputStream entrada) {
        try {
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir")+ "/src/resources" + resource));
            ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(entrada);
            ImageIO.write(image, "png", ArrBytes);
            out.writeBytes("HTTP/1.1 200 OK \r\n"
                    + "Content-Type: image/png \r\n"
                    + "\r\n");
            out.write(ArrBytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * This method is used to get to port of system
     * @return the port of system or 35000 if is empty
     */
    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; // returns default port if heroku-port isn't set(i.e. on localhost)
    }

}

