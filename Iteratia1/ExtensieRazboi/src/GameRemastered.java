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

        /**
         * cautam id-u player-ului ce a pus cartea cu valoarea maxima
         * */
        int winner=-1;
        for(int i=0;i<cartiMasa.getLength();i++){
            if(cartiMasa.getIndexValue(i)==valMax){
                winner=cartiMasa.getId(i);
                break;
            }
        }

        /**
         * adaăugăm cătțile pe care le-a câștigat player-ul în runda curentă
         * */
        for(Player i : jucatori){
            if(i.getId()==winner){
                for(int j=0;j<cartiMasa.getLength();j++){
                    i.addCard(cartiMasa.getCard(j));
                    cartiMasa.remove(j);
                }
            }
        }

    }

    public void winRound(int valMax,RoundCards cartiMasa,List<Player>jucatori){
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
                for(int j=0;j<this.cartiMasa.getLength();j++){
                    i.addCard(this.cartiMasa.getCard(j));
                    this.cartiMasa.remove(j);
                }
            }
        }

    }

    public void war(int valMax){

        //cei x jucatori trebuie sa joaca o runda noua
        //vor pune valMax carti jos, care vor fi adaugate in cartiMasa
        //ultimele x carti puse pe masa vor fi folosite in metoda de castig
        List<Player> jucatoriRazboi = new ArrayList<>();
        int[] save= new int[jucatori.size()];
        int n=0;
        /**
         * salvam id-ul fiecarui player care ia parte la razboi in vectorul save[] ca sa-i putem gasi mai usor pe fiecare
         * in parte
         * */
        for(int i=0;i<cartiMasa.getLength();i++){
            if(cartiMasa.getIndexValue(i)==valMax){
                save[n]=cartiMasa.getId(i);
                n++;
            }
        }
        /**extragem playerii care iau parte la razboi*/
        for(Player i : jucatori){
            for(int j=0;j<n;++j){
                if(i.getId()==save[j]){
                    jucatoriRazboi.add(i);
                }
            }
        }
        RoundCards playedCards = new RoundCards();
        int cardsDown;

        /**
         * Ordinea crescătoare a cărților este:
         * 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9 < 10 < J < Q < K < A
         *
         * Pentru A se vor pune 11 cărți.
         * Pentru J se vor pune 12 cărți.
         * Pentru Q se vor pune 13 cărți.
         * Pentru K se vor pune 14 cărți.
         * Iar pentru celelalte se vor pune exact numărul care este scris pe ele
         *
         * */

        switch (valMax){
            case 11 : cardsDown=12;break;
            case 12 : cardsDown=13;break;
            case 13 : cardsDown=14;break;
            case 14 : cardsDown=11;break;
            default : cardsDown=valMax;break;
        }

        /**
         * verificăm dacă un player are cel puțin numărul de cărți necesare pentru a juca războilul atunci acesta
         * va pune in cartiMasa numărul de cărți necesare-1 iar cartea care se va lua in considerare pentru război
         * va fi pusă în playedCards
         *
         * în caz că nu are numărul necesar de carti atunci acesta va pune cărțile rămase -1 în cartiMasa iar
         * ultima carte o va pune in playedCards
         * */


        for(Player i : jucatoriRazboi) {
            if (i.getManaJucator().getCards().size() >= cardsDown) {
                for (int j = 0; j < cardsDown - 1; ++j) {
                    cartiMasa.putCard(i.getMana(), i.getId());
                }
                playedCards.putCard(i.getMana(), i.getId());
            } else {
                for (int j = 0; j < i.getManaJucator().getCards().size() - 1; ++j) {
                    cartiMasa.putCard(i.getMana(), i.getId());
                }
                playedCards.putCard(i.getMana(), i.getId());
            }
        }


        /**folosim un vector de frecvecventa pentru a determina cartea maxima jucata*/

        int[] frecventa = new int[15];

        for(int i=0;i<playedCards.getLength();i++){
            frecventa[playedCards.getIndexValue(i)]++;
        }

        int valWarMax=0,nrJuc=0;
        for(int i=14;i>1;i--){
            if(frecventa[i]!=0){
                valWarMax=i;
                nrJuc=frecventa[i];
                break;
            }
        }

        /**verificăm dacă avem un câștigător sau dacă avem din nou un caz de egalitate*/

        if(nrJuc==1) winRound(valMax,playedCards,jucatoriRazboi);
        else{
            cartiMasa.putCard(playedCards);
            try {
                war(valWarMax);
            }catch(StackOverflowError e){
                System.out.println("Eroare la war");
            }
        }
    }

    public void playSimple(){


        cartiMasa=new RoundCards();
        while(notOver){//verificăm dacă un joc s-a terminat

           for(Player i : jucatori){  //jucatorii pun cartile pe masa in pachetul cartiMasa
               cartiMasa.putCard(i.getMana(),i.getId());
           }

           int[] frecventa=new int[15]; //initializam frecventa cartilor
           for(int i=0;i<15;i++){
               frecventa[i]=0;
           }

            for(int i=0;i<cartiMasa.getLength();i++){
                try {
                    frecventa[cartiMasa.getIndexValue(i)]++;
                }catch(ArrayIndexOutOfBoundsException e){}
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
