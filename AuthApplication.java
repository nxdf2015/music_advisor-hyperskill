package advisor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

public class AuthApplication extends AbstractApplication{
    private Application application;
    private boolean authorize;


    public AuthApplication(Application application) {
        this.application = application;
        this.authorize = false;
        this.authorizationServerPath = application.getAuthorizationServerPath();
        this.resourceServerPath=application.getResourceServerPath();
    }

    @Override
    public List<PlayList> newRelease() throws ErrorsAPI {

        if (isAuthorize()){
            return   application.newRelease();
        }
        return null;
    }

    @Override
    public List<Album> featured() throws ErrorsAPI {
        if (isAuthorize()){
            return application.featured();
        }
        return null;
    }

    @Override
    public List<Category> categories() throws ErrorsAPI {
        if(isAuthorize()){
            return application.categories();
        }
        return null;
    }

    @Override
    public List<Album> playlists(String playlist) throws ErrorsAPI {
        if (isAuthorize()){

           return  application.playlists(playlist);
        }
        return null;
    }



    public void auth(){
//        String client_id = "308e9cffef344f59b1776c8a6c2b358c";
//        String url_authorize = "https://accounts.spotify.com/authorize?client_id"+client_id+
//                "&redirect_uri=http://localhost:8080"+
//                "&response_type=code";
//        System.out.println(url_authorize);
//        System.out.println();

        try {
            System.out.println("auth :"+ authorizationServerPath);
            Server server = new Server(authorizationServerPath);
            server.startServer();
            System.out.println(server.getUri());
            System.out.println("waiting for code...");

            while (server.getCode() == null) {
                Thread.sleep(100);
            }


            server.stop();
            System.out.println("code receive");
            System.out.println("making http request for access_token...");
            token = server.Token();

            JsonObject data = JsonParser.parseString(token).getAsJsonObject();

            application.setToken(data.get("access_token").getAsString());
            if (token != null) {
                authorize = true;
                System.out.println(token);
                System.out.println();
                System.out.println("---SUCCESS---");
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isAuthorize(){
        if (!authorize){
            System.out.println("Please, provide access for application.");
        }
        return authorize;
    }
}
