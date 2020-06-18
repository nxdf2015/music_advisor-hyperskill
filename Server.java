package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.Flow;

public class Server {

    private final String uriEndPoint;
    private HttpServer httpServer;
    private String code=null;
    private final  String client_id = "308e9cffef344f59b1776c8a6c2b358c";
    private final String client_secret = "ea3236b70ed24e19b38bbaf1ce24606f";
    private String redirect_uri = "http://localhost:8080";
    public String getCode() {
        return code;
    }


    public String getUri(){
         String client_id = "308e9cffef344f59b1776c8a6c2b358c";
         String url_authorize = uriEndPoint+"/authorize?client_id="+client_id+
                "&redirect_uri="+redirect_uri+
                "&response_type=code";

         return url_authorize;
    }

    public Server(String endpoint)   {
        uriEndPoint = endpoint;
    }
    public void startServer() throws IOException {
        this.httpServer =   HttpServer.create();
        httpServer.bind(new InetSocketAddress(8080),0);
        httpServer.createContext("/", new HttpHandler() {


            @Override
            public void handle(HttpExchange httpExchange) {
                 try {
                     String query = httpExchange.getRequestURI().getQuery();
                     code = query;
                     //System.out.println(httpExchange.getRequestURI().toString());

                     if (code != null && code.startsWith("code")) {
                         String message = "Got the code. Return back to your program.";
                         httpExchange.sendResponseHeaders(200, message.length());
                         httpExchange.getResponseBody().write(message.getBytes());
                         httpExchange.close();


                     } else {
                         String message = "Not found authorization code. Try again.";
                         httpExchange.sendResponseHeaders(200, message.length());
                         httpExchange.getResponseBody().write(message.getBytes());
                         httpExchange.close();
                     }

                 }
                 catch (Exception e){

                 }







            }
        });

        httpServer.start();
    }




    public String Token(){
        String uri =uriEndPoint+"/api/token";

        String requestBody = "grant_type=authorization_code&"
                +this.code+"&redirect_uri="+redirect_uri;
       HttpClient httpClient = HttpClient.newBuilder().build();

       HttpRequest httpRequest = HttpRequest.newBuilder()
               .header("Content-Type","application/x-www-form-urlencoded")
               .header("Authorization"," Basic " + Base64.getEncoder().encodeToString((client_id+":"+client_secret).getBytes() ))
               .uri(URI.create(uri))
               .POST(HttpRequest.BodyPublishers.ofString(requestBody))
               .build();

        try {
           HttpResponse<String> response =  httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

          return null;
    }


    public void stop() {
        httpServer.stop(0);
        System.out.println("stop server");
    }
}
