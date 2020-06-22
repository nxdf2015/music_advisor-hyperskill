package advisor;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Map<String,String> data;
    private String defaultAuthorizationPath="https://accounts.spotify.com";
    private String  defaultResourcepath = "https://api.spotify.com";


    public Parser() {
        data = new HashMap<>();
    }

    public void getOption(String[] args){
        int i =0;
       while(i <args.length){
            if (args[i].startsWith("-")&& !args[i+1].startsWith("-")){
                data.put(args[i].replaceAll("-",""),args[i+1]);
                i+=2;
            }
        }

    }

    public String getAuthorizationPath(){
       String value =   data.get("access");
       return value == null ? defaultAuthorizationPath : value;
    }

    public String getResourcePath(){
        String value = data.get("resource");
        return value == null ? defaultResourcepath : value;
    }

    public int getItemByPage(){
        String value = data.get("page");
        int number = 5;
        try{
             number = Integer.parseInt(value);
            return number;
        }
        catch (NumberFormatException e){

        }
        finally {
            return number;
        }

    }

}
