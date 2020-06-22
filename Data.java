package advisor;

import java.util.List;
import java.util.Optional;

public abstract class Data {
    public static int itemByPage;


    public static int getItemByPage() {
        return itemByPage;
    }

    public static void setItemByPage(int itemByPage) {
        Data.itemByPage = itemByPage;
    }

    protected int size;
    private int currentPosition;
    public  List data;
    private int currentPage;

    private boolean previousIsNext= false;
    public Data() {
        this.currentPage = 0;
    }

    public   int getNumberPages(){
        return  (int) Math.ceil(size  / (double)getItemByPage());

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Optional<List> next(){
            int lastPosition;

                lastPosition = Math.min(currentPosition + getItemByPage(),data.size());


            List result = data.subList(currentPosition,lastPosition);
            if (result.size()>0){
                currentPosition=lastPosition;
                currentPage++;
                previousIsNext = true;
                return Optional.of(result);
            }

        return Optional.empty();
    }
    private void info(){
        System.out.println("current position  "+currentPosition);
        System.out.println("current Page  " + currentPage);
        System.out.println(getItemByPage());
    }
    public Optional<List> previous(){
           int headPosition;
           int lastPosition ;
            if(previousIsNext){
                headPosition = Math.max(currentPosition - 2* getItemByPage(),0);
                lastPosition = Math.max(currentPosition - getItemByPage(),0);
            }
            else {
                headPosition = Math.max(currentPosition -  getItemByPage(),0);
                lastPosition = currentPosition;
            }


            List result = data.subList(headPosition,lastPosition);
            if(result.size() > 0){
                currentPosition = headPosition;
                currentPage--;
                previousIsNext=false;
                return Optional.of(result);
            }

        return Optional.empty();
    }

    public abstract void save(List data);


}
