package advisor;




import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Application extends AbstractApplication{




    @Override
    public void newRelease() throws ErrorsAPI {
        System.out.println("---NEW RELEASES---");
        JsonObject albums = query(new_releasesPath).get("albums").getAsJsonObject();
        for(JsonElement item : albums.getAsJsonArray("items")){
            JsonObject data = item.getAsJsonObject();
            String  link =  data.getAsJsonObject("external_urls").get("spotify").getAsString();
            List<String> listName  = new ArrayList<>();
            for (JsonElement elt : data.getAsJsonArray("artists")){
                JsonObject artist = elt.getAsJsonObject();
                listName.add(artist.get("name").getAsString());

            }
          // JsonObject  artist=  data.getAsJsonArray("artists").get(0).getAsJsonObject();

            String nameArtist = String.join(", ",listName);


          String nameAlbum = data.get("name").getAsString();
            System.out.println(nameAlbum);
            System.out.println("["+nameArtist+"]");
            System.out.println(link);
            System.out.println();
        }
//        System.out.println("---NEW RELEASES---\n" +
//                "Mountains [Sia, Diplo, Labrinth]\n" +
//                "Runaway [Lil Peep]\n" +
//                "The Greatest Show [Panic! At The Disco]\n" +
//                "All Out Life [Slipknot]\n");
    }

    @Override
    public void featured() throws ErrorsAPI {
        System.out.println("---FEATURED---");
        JsonObject playlists = query(featuredPath).getAsJsonObject("playlists");
        for(JsonElement items : playlists.getAsJsonArray("items")){
            JsonObject data  = items.getAsJsonObject();
            String name = data.get("name").getAsString();
            String link = data.getAsJsonObject("external_urls").get("spotify").getAsString();
            System.out.println(name);
            System.out.println(link);
            System.out.println();
        }
//                "Mellow Morning\n" +
//                "Wake Up and Smell the Coffee\n" +
//                "Monday Motivation\n" +
//                "Songs to Sing in the Shower\n");
    }

    @Override
    public void categories() throws ErrorsAPI {
        System.out.println("---CATEGORIES---") ;
        JsonObject category = query(categoriesPath).getAsJsonObject("categories");
        for(JsonElement items : category.getAsJsonArray("items")){
            JsonObject data = items.getAsJsonObject();
            String nameCategory = data.get("name").getAsString();
            System.out.println(nameCategory);

        }
//                "Top Lists\n" +
//                "Pop\n" +
//                "Mood\n" +
//                "Latin\n");
    }

    @Override
    public void playlists(String playlist) throws ErrorsAPI {
        System.out.printf("---%s PLAYLISTS---\n",playlist.toUpperCase());
        Optional<String> id = findId(playlist);

        if (id.isEmpty()){
            System.out.println("Unknown category name.");
        }
        else {

            String path =  categoriesPath + "/" + id.get() +"/playlists";
            JsonObject result = query(path).getAsJsonObject("playlists");
            for(JsonElement items : result.getAsJsonArray("items")){
                JsonObject data = items.getAsJsonObject();
                String link = data.getAsJsonObject("external_urls").get("spotify").getAsString();
                String name = data.get("name").getAsString();
                System.out.println(name);
                System.out.println(link);
                System.out.println();
            }

        }
//                "Walk Like A Badass  \n" +
//                "Rage Beats  \n" +
//                "Arab Mood Booster  \n" +
//                "Sunday Stroll");
    }

    private Optional<String>  findId(String playlist) throws ErrorsAPI {
        JsonObject category = query(categoriesPath).getAsJsonObject("categories");
        for(JsonElement items : category.getAsJsonArray("items")){
            JsonObject data = items.getAsJsonObject();
            String nameCategory = data.get("name").getAsString();
            if (nameCategory.equals(playlist)){
                return Optional.of(data.get("id").getAsString());
            }

        }
        return  Optional.empty();
    }


}

