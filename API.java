package advisor;

import java.util.List;

public interface API {
    public List<PlayList> newRelease() throws ErrorsAPI;
    public List<Album> featured() throws ErrorsAPI;
    public List<Category> categories() throws ErrorsAPI;
    public List<Album> playlists(String playlist) throws ErrorsAPI;

}
