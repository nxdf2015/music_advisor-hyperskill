package advisor;

import java.util.List;
import java.util.Optional;

import static advisor.ACTION.AUTH;

public class Controller {
    private final String authorizationPath;
    private final String resourcePath;
    private final int itemByPage;
    private  Data data;
    private View view;
    private AuthApplication authApplication;
    private boolean exit;

    public Controller(String authorizationPath, String resourcePath, int itemByPage) {
        this.authorizationPath = authorizationPath;
        this.resourcePath = resourcePath;
        this.exit = false;
        this.itemByPage = itemByPage;

        Data.setItemByPage(itemByPage);

        defineApplication();
    }


    private void defineApplication(){
        if (authApplication == null) {
            Application application = new Application();
            application.setAuthorizationServerPath(authorizationPath);
            application.setResourceServerPath(resourcePath);
            authApplication = new AuthApplication(application);
        }

    }

    public void executeAction(ACTION select) throws ErrorsAPI {

        if (select == AUTH){
            authApplication.auth();
        }
        else {
            switch (select) {
                case NEW:
                    this.data = new PlayListStorage();
                    this.data.save(authApplication.newRelease());

                    this.view.show(this.getNext(),data.getCurrentPage(),data.getNumberPages());
                    break;

                case FEATURED:
                    this.data = new AlbumStorage();
                    this.data.save(authApplication.featured());
                    this.view.show(this.getNext(), data.getCurrentPage(), data.getNumberPages());
                    break;

                case CATEGORIES:
                    this.data = new CategoryStorage();
                    this.data.save(authApplication.categories());
                    view.show(this.getNext(), data.getCurrentPage(), data.getNumberPages());
                    break;

                case NEXT:
                    Optional<List> nextItem = getNext();
                    view.show(nextItem, data.getCurrentPage(), data.getNumberPages());
                    break;
                case PREV:
                    Optional<List> prevItem =  getPREV();

                    view.show(prevItem, data.getCurrentPage(), data.getNumberPages());
                    break;
                case PLAYLISTS:

                    // System.out.println(playlist);

                    break;
                default:
                    System.out.println("---GOODBYE!---");
                    exit = true;
                    break;
            }

    }
}

    private Optional<List> getPREV() {
        if (data != null) {
            Optional<List> items = data.previous();
            if (items.isPresent() && items.get().size()>0) {
                return Optional.of(items.get());
            }


        }

        return Optional.empty();

    }

    private Optional<List> getNext() {

        if (data != null) {
            Optional<List> items = data.next();
            if (items.isPresent() && items.get().size()>0) {
                return Optional.of(items.get());
            }


        }

        return Optional.empty();

    }

    public void executeAction(ACTION select, String playlist) throws ErrorsAPI {
        this.data = new AlbumStorage();
        this.data.save(authApplication.playlists(playlist));
        this.view.show(this.getNext(), data.getCurrentPage(), data.getNumberPages());
    }

    public boolean isActive(){
        return !exit;
    }

    public void setView(View view) {
        this.view = view;
    }
}
