package advisor;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractApplication implements API{
    protected String token;
    protected String authorizationServerPath;
    protected String resourceServerPath;
    protected String new_releasesPath = "/v1/browse/new-releases";
    protected String featuredPath = "/v1/browse/featured-playlists";
    protected String categoriesPath ="/v1/browse/categories";

    protected  String getUriResource(String path){
        return resourceServerPath  +path;
    }

    protected JsonObject query(String path) throws ErrorsAPI {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest =HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + getToken())
                .uri(URI.create(getUriResource(path)))
                .GET()
                .build();
        try {
          HttpResponse<String> response =   httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

          JsonObject json =  JsonParser.parseString(response.body()).getAsJsonObject();
          if (json.has("error")){
              System.out.println(json);
              String msg = json.getAsJsonObject("error").get("message").getAsString();
              throw new  ErrorsAPI(msg);
          }
          else {
              return json;
          }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String response = "{\"exception\":" + e.getMessage()+"}";
            return JsonParser.parseString(response).getAsJsonObject();
        }
    }






    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorizationServerPath() {
        return authorizationServerPath;
    }

    public void setAuthorizationServerPath(String authorizationServerPath) {
        this.authorizationServerPath = authorizationServerPath;
    }

    public String getResourceServerPath() {
        return resourceServerPath;
    }

    public void setResourceServerPath(String resourceServerPath) {
        this.resourceServerPath = resourceServerPath;
    }


}
