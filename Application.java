package advisor;

public class Application implements API{
    private String endPointsUri;

    @Override
    public void newRelease() {
        System.out.println("---NEW RELEASES---\n" +
                "Mountains [Sia, Diplo, Labrinth]\n" +
                "Runaway [Lil Peep]\n" +
                "The Greatest Show [Panic! At The Disco]\n" +
                "All Out Life [Slipknot]\n");
    }

    @Override
    public void featured() {
        System.out.println("---FEATURED---\n" +
                "Mellow Morning\n" +
                "Wake Up and Smell the Coffee\n" +
                "Monday Motivation\n" +
                "Songs to Sing in the Shower\n");
    }

    @Override
    public void categories() {
        System.out.println("---CATEGORIES---\n" +
                "Top Lists\n" +
                "Pop\n" +
                "Mood\n" +
                "Latin\n");
    }

    @Override
    public void playlists(String playlist) {
        System.out.println("---MOOD PLAYLISTS---\n" +
                "Walk Like A Badass  \n" +
                "Rage Beats  \n" +
                "Arab Mood Booster  \n" +
                "Sunday Stroll");
    }

    @Override
    public void setEndPoint(String uri) {
        this.endPointsUri = uri;

    }

    @Override
    public String  getEndPoint() {
        return endPointsUri;
    }


}
