import java.util.ArrayList;
import java.util.List;

class RoundCards {
    private List<Card> cartiJos;
    private List<Integer> playerID; // 0,1,2

    RoundCards(){
        cartiJos = new ArrayList<>();
        playerID = new ArrayList<>();
    }

    public void putCard(Card carte, int id){
        cartiJos.add(carte);
        playerID.add(id);
        //System.out.println("Introducere reusita" + id +"   "+ carte.getNumber());
    }

    /*public Card getById(int id){
        return playerID.get(id);
    }*/

    public void putCard(RoundCards aux){
        for(int i=0;i<aux.size();++i){
            cartiJos.add(aux.getCard(i));
            playerID.add(aux.getId(i));
        }
    }

    public int getLength(){
        return cartiJos.size();
    }

    public void delete(){
        while(cartiJos.size()>=1){
            cartiJos.remove(0);
            playerID.remove(0);
        }
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
        //System.out.println(cartiJos.get(index).getNumber()+ "  numar");
        try{
        return cartiJos.get(index).getNumber();
    }catch(NullPointerException e){
           // System.out.println(cartiJos.get(index).getNumber()+" numar carti jos");
            return -1;
        }

    }

    public int size(){
        try{
        return cartiJos.size();
    }catch(StackOverflowError e){}
        return 0;
    }

    @Override
    public String toString() {
        return "RoundCards{" +
                "cartiJos=" + cartiJos +
                ", playerID=" + playerID +
                '}';
    }
}
