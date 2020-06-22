package advisor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayList {
    private List<String> namesArtist;
    private Album album;

    public PlayList(String link,String nameAlbum) {
        this.album =new Album(link,nameAlbum);
        this.namesArtist = new ArrayList<>();
    }

    public void addArtist(String name){
        this.namesArtist.add(name);
    }


    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(album.getName())
        .append(System.lineSeparator())
        .append("[")
         .append(String.join(", ",namesArtist))
        .append("]")
        .append(System.lineSeparator())
        .append(album.getLink())
        .append(System.lineSeparator());
        return builder.toString();
    }
}
