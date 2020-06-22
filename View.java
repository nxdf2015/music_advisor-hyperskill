package advisor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    private int currentPage;
    private int numberPage;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void show(Optional<List> nextItem, int currentPage, int numberPages) {
        this.currentPage = currentPage;
        this.numberPage = numberPages;

        if (nextItem.isEmpty()){
            System.out.println("No more pages.");

        }
        else {
            List data = nextItem.get();
            if (data.get(0) instanceof Album){
                showAlbum(data);
            }
            else if (data.get(0) instanceof  PlayList){
                showPlayList(data);
            }
            else {
                showCategory(data);
            }
            header();
        }
    }

    private void header(){

        if (currentPage<= numberPage && currentPage>0) {
            System.out.println("---PAGE " + currentPage + " OF " + numberPage + "---");
        }

    }
    private void showCategory(List<Category> data) {

        for(Category category :data){
            System.out.println(category);
        }
    }


    private void showPlayList(List<PlayList> data) {
        for(PlayList playList : data){
            System.out.println(playList);
        }
    }

    private void showAlbum(List<Album> data) {
        for(Album album : data){
            System.out.println(album);
            System.out.println();
        }
    }

    public ACTION getAction() throws EnumConstantNotPresentException {

        return ACTION.valueOf(scanner.next().strip().toUpperCase());
    }

    public String getLine() {
        return scanner.nextLine().trim();
    }
}
