package advisor;

import java.util.List;

public class CategoryStorage extends Data {



    @Override
    public void save(List data) {
        this.data = (List<Category>) data;
        this.size=data.size();
    }
}
