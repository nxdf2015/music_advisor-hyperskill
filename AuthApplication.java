package advisor;

import java.io.IOException;

public class AuthApplication  implements API{
    private Application application;
    private boolean authorize;
    private String endPointUri;

    public AuthApplication(Application application) {
        this.application = application;
        this.authorize = false;
        this.endPointUri = application.getEndPoint();

    }

    @Override
    public void newRelease() {
        if (isAuthorize()){
            application.newRelease();
        }
    }

    @Override
    public void featured() {
        if (isAuthorize()){
            application.featured();
        }
    }

    @Override
    public void categories() {
        if(isAuthorize()){
            application.categories();
        }
    }

    @Override
    public void playlists(String playlist) {
        if (isAuthorize()){
            application.playlists(playlist);
        }
    }

    @Override
    public void setEndPoint(String uri) {

    }

    @Override
    public String getEndPoint() {
        return endPointUri;
    }

    public void auth(){
//        String client_id = "308e9cffef344f59b1776c8a6c2b358c";
//        String url_authorize = "https://accounts.spotify.com/authorize?client_id"+client_id+
//                "&redirect_uri=http://localhost:8080"+
//                "&response_type=code";
//        System.out.println(url_authorize);
//        System.out.println();

        try {
            System.out.println("auth :"+endPointUri);
            Server server = new Server(endPointUri);
            server.startServer();
            System.out.println(server.getUri());
            System.out.println("waiting for code...");

            while (server.getCode() == null) {
                Thread.sleep(100);
            }


            server.stop();
            System.out.println("code receive" + server.getCode());
            System.out.println("making http request for access_token...");
            String token = server.Token();
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
