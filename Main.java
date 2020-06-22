package advisor;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import static advisor.ACTION.AUTH;
import static advisor.ACTION.PLAYLISTS;

public class Main {
    public static void main(String[] args) {


        boolean authorize = false;
       // Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        Parser parser =new Parser();
        parser.getOption(args);
        String authorizationPath = parser.getAuthorizationPath();
        String resourcePath = parser.getResourcePath();
        int itemByPage = parser.getItemByPage();
       // System.out.println(itemByPage+"---------------"+authorizationPath+"-----------"+resourcePath);
        View view  = new View();

        Controller controller = new Controller(authorizationPath,resourcePath,itemByPage);
        controller.setView(view);

//
//        Application application = new Application();
//        application.setAuthorizationServerPath(authorizationPath);
//        application.setResourceServerPath(resourcePath);
//
//        AuthApplication  authApplication= new AuthApplication(application);

        while (controller.isActive()) {


           try {
               ACTION  select =view.getAction();
             //  ACTION select = ACTION.valueOf(scanner.next().strip().toUpperCase());

               if (select == PLAYLISTS){
                   String playlist = view.getLine();
                   controller.executeAction(select,playlist);
               }
               else {
                   controller.executeAction(select);
               }


           }
           catch (EnumConstantNotPresentException | IllegalArgumentException e){
               System.out.println("selection invalid");
              e.printStackTrace();
           }
           catch (ErrorsAPI e){
               System.out.println(e.getMessage());
           }

        }
    }
}

