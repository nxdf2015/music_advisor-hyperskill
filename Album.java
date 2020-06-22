package advisor;

public class Album {
    private final String link,name;

    public Album(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return           name+ System.lineSeparator()+ link;
    }
}
