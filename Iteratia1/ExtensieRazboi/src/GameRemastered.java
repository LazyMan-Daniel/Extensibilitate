import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameRemastered {

    //52 carti: max 52 playeri
    private List<Player> jucatori;
    private int nrCarti;
    private int nrCartiPlayer;
    private boolean existaPrizonier;
    private Deck carti;
    private RoundCards cartiMasa;
    private boolean notOver;

    public GameRemastered(int nrPlayers){
        int id=0;
        this.nrCartiPlayer=52/nrPlayers;
        this.existaPrizonier=false;
        carti=new Deck();
        notOver=true;
        carti.shuffleDeck();
        jucatori=new ArrayList<>();
        //52->impartit la 4 jucatori-> 13carti pe jucator
        for(int i=0;i<nrPlayers;i++){

            jucatori.add(new Player(nrCartiPlayer,carti,id));
            id++;
        }
    }

    public void winRound(int valMax){
        int winner=-1;
        for(int i=0;i<cartiMasa.getLength();i++){
            if(cartiMasa.getIndexValue(i)==valMax){
                winner=cartiMasa.getId(i);
                break;
            }
        }

        for(Player i : jucatori){
            if(i.getId()==winner){
                for(int j=0;j<cartiMasa.getLength();j++){
                    i.addCard(cartiMasa.getCard(j));
                    cartiMasa.remove(j);
                }
            }
        }

    }

    public void war(int valMax){

        //cei x jucatori trebuie sa joaca o runda noua
        //vor pune valMax carti jos, care vor fi adaugate in cartiMasa
        //ultimele x carti puse pe masa vor fi folosite in metoda de castig

        for(int i=0;i<cartiMasa.getLength();i++){
            if(cartiMasa.getIndexValue(i)==valMax){

            }
        }

    }



    public void playSimple(){

        cartiMasa=new RoundCards();
        while(notOver){

           for(Player i : jucatori){  //jucatorii pun cartile pe masa in pachetul cartiMasa
               cartiMasa.putCard(i.getMana(),i.getId());
           }

           int[] frecventa=new int[15]; //initializam frecventa cartilor
           for(int i=0;i<15;i++){
               frecventa[i]=0;
           }

            for(int i=0;i<cartiMasa.getLength();i++){
                frecventa[cartiMasa.getIndexValue(i)]++;
            }


            int valMax=0,nrJuc=0;
            for(int i=14;i>1;i--){
                if(frecventa[i]!=0){
                    valMax=i;
                    nrJuc=frecventa[i];
                    break;
                }
            }

            if(nrJuc==1) winRound(valMax);
            else war(valMax);

            for(Player i : jucatori){
                if(i.getNrCards()==0){
                    jucatori.remove(i);
                }
            }

            if(jucatori.size()==1){
                notOver=false;
                System.out.println("Jucatorul " + jucatori.get(0).getId() + " a castigat" );
            }


        }



    }



    public void playGame(){


        //if(existaPrizonier)
          //  playExtenestion();
        //else
            playSimple();

        //daca exista carte prizonier
            //daca nu-> jocul simplu + castigatorul alege cartea prizonier
            //daca da-> timer-ul (alege daca pui prizonier sau nu)
                      // caz de egalitate (tot cu timer)
                      // castigatorul alege cartea prizonier
    }


}
