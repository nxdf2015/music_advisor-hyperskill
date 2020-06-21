package advisor;

public interface API {
    public void newRelease() throws ErrorsAPI;
    public void featured() throws ErrorsAPI;
    public void categories() throws ErrorsAPI;
    public void playlists(String playlist) throws ErrorsAPI;

}
