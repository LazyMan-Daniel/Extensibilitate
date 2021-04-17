import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

public class GameRemastered {

    //52 carti: max 52 playeri
    private List<Player> jucatori;
    private int nrCarti;
    private int nrCartiPlayer;
    private boolean existaPrizonier;
    private Deck carti;
    private Deck cartiCastigator;
    private RoundCards cartiMasa;
    private boolean notOver;
    List<Player> jucatoriRazboi;

    public GameRemastered(int nrPlayers){
        int id=0;
        this.nrCartiPlayer=52/nrPlayers;
        this.existaPrizonier=false;
        jucatoriRazboi=new ArrayList<>();
        carti=new Deck();
        cartiCastigator=new Deck();
        notOver=true;
        carti.shuffleDeck();
        jucatori=new ArrayList<>();
        //52->impartit la 4 jucatori-> 13carti pe jucator
        for(int i=0;i<nrPlayers;i++){

            jucatori.add(new Player(nrCartiPlayer,carti,id));
           // System.out.println(jucatori.toString());
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
                    cartiMasa.remove(j); j--;
                }
            }
        }

    }

    public void winRound(int valMax,RoundCards cartiMasa,List<Player>jucatori){
        int winner=-1;
        System.out.println("WAAAARUAAWG");
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
                    cartiMasa.remove(j); j--;
                }
                for(int j=0;j<this.cartiMasa.getLength();j++){
                    i.addCard(this.cartiMasa.getCard(j));
                    this.cartiMasa.remove(j); j--;
                }
            }
        }

    }

    public void war(RoundCards ultimeleCarti, int valMax, int nrJuc){

        /**
         * war: x jucatori cu element maxim n
         *
         *   jucatorul Pi- are >=n : va pune n carti
         *               - are 0< nr carti <n: va pune nr carti
         *               - are 0 carti: cartea de varf va ramane valabila
         */

        //jucatoriRazboi

        int[] save= new int[jucatori.size()+1];
        for(int i=0;i< jucatori.size();i++) save[i]=-5;
        int n=0;
        /**
         * salvam id-ul fiecarui player care ia parte la razboi in vectorul save[] ca sa-i putem gasi mai usor pe fiecare
         * in parte
         * */



        for(int i=0;i<cartiMasa.getLength();i++){
           // System.out.println("101 "+ cartiMasa.getLength());
            if(cartiMasa.getIndexValue(i)==valMax){
                save[n]=cartiMasa.getId(i);
                n++;
            }
        }

        //ultimeleCarti.eliberare();

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


        System.out.println(ultimeleCarti.toString());
        RoundCards ultimeleCarti2 = new RoundCards();
        for(Player i : jucatoriRazboi) {
            if (i.getManaJucator().getCards().size() >= cardsDown) {
                for (int j = 0; j < cardsDown - 1; ++j) {
                    cartiMasa.putCard(i.getMana(), i.getId());
                }
                ultimeleCarti2.putCard(i.getMana(), i.getId());
            } else if (i.getManaJucator().getCards().size() == 0) {
                for (int j = 0; j < ultimeleCarti.size(); ++j) {
                    if (ultimeleCarti.getId(j) == i.getId()) {
                        ultimeleCarti2.putCard(ultimeleCarti.getCard(j), i.getId());
                        ultimeleCarti.remove(j);
                    }
                }
            } else if (i.getManaJucator().getCards().size() != 0 && i.getManaJucator().getCards().size() < cardsDown) {
                for (int j = 0; j < i.getManaJucator().getCards().size() - 1; ++j) {
                    cartiMasa.putCard(i.getMana(), i.getId());
                }
                ultimeleCarti2.putCard(i.getMana(), i.getId());
            }
        }



    /**folosim un vector de frecvecventa pentru a determina cartea maxima jucata*/

        int[] frecventa = new int[15];
        for(int i=0;i<15;i++) frecventa[i]=0;

        for(int i=0;i<playedCards.getLength();i++){
            try {
                frecventa[playedCards.getIndexValue(i)]++;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Eroare la frecventa line 169");
            }
            }

        int valWarMax=0;
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
            System.out.println("War nr2");
            cartiMasa.putCard(playedCards);
            try {

                war(ultimeleCarti,valWarMax,2);
            }catch(StackOverflowError e){
                System.out.println("Eroare la war");
            }
        }
    }


    public void scoatePierzatorii(){
        /*for(Player i:jucatori){
            System.out.println("Nr Carti "+ i.getNrCards());

        } */

        for(int i=0;i< jucatori.size();i++){
            if(jucatori.get(i).getNrCards()==0) {
                jucatori.remove(i);
                i--;
            }
        }

    }


    public void playSimple(){


        cartiMasa=new RoundCards();
        while(notOver){//verificăm dacă un joc s-a terminat

           for(Player i : jucatori){  //jucatorii pun cartile pe masa in pachetul cartiMasa

               if(i.getNrCards()==0){
                   jucatori.remove(i);
               }
               else {
                   cartiMasa.putCard(i.getMana(), i.getId());

               }
           }

           int[] frecventa=new int[16]; //initializam frecventa cartilor
           for(int i=0;i<16;i++){
               frecventa[i]=0;
           }

            for(int i=0;i<cartiMasa.getLength();i++){

                try {
                    frecventa[cartiMasa.getIndexValue(i)]++;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(cartiMasa.getIndexValue(i)+ " line214");
                    System.out.println("Eroare linie 215");
                }
                }


            int valMax=0,nrJuc=0;
            for(int i=14;i>1;i--){
                if(frecventa[i]!=0){
                    valMax=i;
                    nrJuc=frecventa[i];
                    break;
                }
            }

            if(nrJuc==1) {
                winRound(valMax);
                System.out.println("[OK] Castigator line240");
                for(Player i: jucatori )
                System.out.println(i.toString());
            }


            else {
                System.out.println("[OK]Lupta line243");
                RoundCards deTrimis= new RoundCards();
                deTrimis=cartiMasa;
                war(deTrimis,valMax,nrJuc);


                for(Player i: jucatori )
                System.out.println(i.toString());
            }


            System.out.println("[OK]Finalizeaza line248");


            /*
            try{
            for(Player i : jucatori){

                if(i.getNrCards()==0){
                    try {
                        jucatori.remove(i);
                    }catch(ConcurrentModificationException e){
                        System.out.println("Eroare for line 247");
                    }
                    }
            } }catch(ConcurrentModificationException e){
                System.out.println("Eroare la for line 251");
            } */

            if(jucatori.size()==1){
                notOver=false;
                System.out.println("Jucatorul " + jucatori.get(0).getId() + " a castigat" );
            }

            scoatePierzatorii();
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
