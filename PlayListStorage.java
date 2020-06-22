package advisor;

import java.util.List;

public class PlayListStorage extends Data{

    @Override
    public void save(List data) {
        this.data = (List<PlayList>)data;
        this.size=data.size();
    }
}
