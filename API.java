package advisor;

public interface API {
    public void newRelease();
    public void featured();
    public void categories();
    public void playlists(String playlist);
    public void setEndPoint(String uri);
    public String getEndPoint();
}
