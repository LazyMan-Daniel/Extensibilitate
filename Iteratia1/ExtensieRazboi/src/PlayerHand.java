import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerHand {

    private Deck deck;
    private int noOfCards = 52; //carti ramase in pachet
    private int initialNumber; //numarul de carti pe care le primeste jucatorul la inceput
    private List<Card> playersCards; //cartile din mana jucatorului
    private boolean isTurn; //true cand e randul jucatorului
    private Card prisoner;

    public PlayerHand(int initialNumber, Deck carti) {
        this.initialNumber = initialNumber;
        deck=carti;
        drawInitialCards();

           // System.out.println(playersCards);

    }
    public List<Card> getCards(){
        return playersCards;
    }

    public int nrCarti(){
        return playersCards.size();
    }

    public void setInitialNumber(int initialNumber) {
        this.initialNumber = initialNumber;
    }

    public int getInitialNumber() {
        return initialNumber;
    }

    public boolean getTurn() {
        return isTurn;
    }

    public void setTurn() {
        isTurn = true;
    }

    /*public void makeDeck(){
        deck=new ArrayList<>();
        for(int i=2;i<=14;i++) {
            for (int j = 1; j <= 4; j++) {
                deck.add(new Card(i, j));
            }
        }
    }*/

    public void drawInitialCards(){ //impartirea cartilor la inceput
        int initialNumber = getInitialNumber();
        playersCards = new ArrayList<>();
        for(int i=0; i<initialNumber; i++){
            playersCards.add(deck.extractOneCard());
            Random rnd=new Random();
            int index = rnd.nextInt(noOfCards);
            playersCards.add(deck.get(index)); //adauga in mana jucatorului cartea de pe poz index din pachet
            noOfCards--;
            deck.remove(index);
        }


    }

    public void addCard(Card carte){
        playersCards.add(carte);
    }


    public Card placeCard(){  // trebuie adaptata functia in cazul in care jucatorul castiga mai multe carti, nu e random
        if(playersCards.size()<1) {
            Card carte = playersCards.get(0);
            playersCards.remove(0);

            return carte;
        }
        else throw new IndexOutOfBoundsException("Nu merge");

       /* Random rnd=new Random();
        int index = rnd.nextInt(noOfCards);
        playersCards.add(deck.get(index)); //adauga in mana jucatorului cartea de pe poz index din pachet
        noOfCards--;
        deck.remove(index);
        */
    }


    public void putCard(int index){ //fiecare jucator are un deck diferit, trebuie revizuit
        //ar trebui sa puna o carte jos in functie de reguli
        Card chosenCard = playersCards.get(index);
        int color = chosenCard.getColor();
        int number = chosenCard.getNumber();
        System.out.println("Number= " + number + ",  culoare= " + color);
        //apeleaza functia de reguli cu number si color ca parametri; daca valideaza ca fiind corect pune cartea jos
        //altfel trimite un mesaj ca trebuie sa alegem alta carte

        //deck.add(chosenCard);
        noOfCards++;
        playersCards.remove(index);

        System.out.println("Number of cards: " + playersCards.size());

    }

    public void printCards(){
        System.out.println(playersCards.toString());
    }
}
