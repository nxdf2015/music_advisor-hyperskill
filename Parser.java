package advisor;

public class Parser {
    private String defaultEndPoint="https://accounts.spotify.com";
    public String getOption(String[] args){
        if (args[0].equals("-access") && args.length==2){
            return args[1];
        }
        else {
            return  defaultEndPoint;
        }
    }
}
