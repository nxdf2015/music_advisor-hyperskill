package advisor;

import java.util.ArrayList;
import java.util.List;

public class AlbumStorage extends Data{


    @Override
    public void save(List data) {
        this.data = ((List<Album>) data);
        this.size = data.size();
    }
}
