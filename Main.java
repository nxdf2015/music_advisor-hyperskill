package advisor;

import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

import static advisor.ACTION.AUTH;

public class Main {
    public static void main(String[] args) {




        boolean authorize = false;
        Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        Parser parser =new Parser();
        parser.getOption(args);
        String authorizationPath = parser.getAuthorizationPath();
        String resourcePath = parser.getResourcePath();

        Application application = new Application();
        application.setAuthorizationServerPath(authorizationPath);
        application.setResourceServerPath(resourcePath);

        AuthApplication  authApplication= new AuthApplication(application);

        while (!exit) {

           try {
               ACTION select = ACTION.valueOf(scanner.next().strip().toUpperCase());
               if (select == AUTH){
                   authApplication.auth();
               }
               else {
               switch (select) {
                   case NEW:
                       authApplication.newRelease();
                       break;
                   case FEATURED:
                       authApplication.featured();
                       break;
                   case CATEGORIES:
                       authApplication.categories();

                       break;
                   case PLAYLISTS:
                       String playlist = scanner.nextLine().trim();
                      // System.out.println(playlist);
                       authApplication.playlists(playlist);
                       break;
                   default:
                       System.out.println("---GOODBYE!---");
                       exit = true;
                       break;
               }
               }
           }
           catch (EnumConstantNotPresentException | IllegalArgumentException e){
               System.out.println("selection invalid");
           }
           catch (ErrorsAPI e){
               System.out.println(e.getMessage());
           }

        }
    }
}

