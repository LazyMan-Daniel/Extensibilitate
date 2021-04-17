import java.util.ArrayList;
import java.util.List;

class RoundCards {
    private List<Card> cartiJos;
    private List<Integer> playerID;

    RoundCards(){
        cartiJos = new ArrayList<>();
        playerID = new ArrayList<>();
    }

    public void putCard(Card carte, int id){
        cartiJos.add(carte);
        playerID.add(id);
    }

    public void putCard(RoundCards aux){
        for(int i=0;i<aux.size();++i){
            this.putCard(aux.getCard(i),aux.getId(i));
        }
    }

    public int getLength(){
        return cartiJos.size();
    }

    public void remove(int index){
        cartiJos.remove(index);
        playerID.remove(index);
    }

    public Card getCard(int index){
        return cartiJos.get(index);
    }


    public int getId(int index){
        return playerID.get(index);
    }

    public int getIndexValue(int index){
        try{
        return cartiJos.get(index).getNumber();
    }catch(NullPointerException e){}
        return -1;
    }

    public int size(){
        try{
        return cartiJos.size();
    }catch(StackOverflowError e){}
        return 0;
    }
}
