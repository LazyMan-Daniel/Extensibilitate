import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    private int cardsLeft;
    public Deck(){
        deck=new ArrayList<>();
        for(int i=2;i<=14;i++){
            for(int j=1;j<=4;j++){
                deck.add(new Card(i,j));
            }
        }
        cardsLeft=52;
    }
    public Deck(List<Card> cardList){
        deck=new ArrayList<>();
        deck.addAll(cardList);
        cardsLeft=cardList.size();
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
        cardsLeft=deck.size();
    }
    public void shuffleDeck(){
        int[] choosed=new int[53];
        Random rnd=new Random();
        int numberofCards,position;
        for(int i=0;i<50;i++){
            numberofCards=rnd.nextInt(this.cardsLeft);
            position=rnd.nextInt(this.cardsLeft);
            if(position%4==0){position=52; }
            if(position<numberofCards)position=numberofCards+rnd.nextInt(this.cardsLeft-numberofCards);
            rotateCards(numberofCards,position);
        }
    }
    public void printDeck(){
        System.out.println(deck);
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public void setCardsLeft(int cardsLeft) {
        this.cardsLeft = cardsLeft;
    }

    private void rotateCards(int n, int pos){
        List<Card> shufled=new ArrayList<>();

        for(int i=n;i<pos;i++){
            shufled.add(deck.get(i));
        }
        for(int i=0;i<n;i++){
            shufled.add(deck.get(i));
        }
        for(int i=pos;i<this.cardsLeft;i++){
            shufled.add(deck.get(i));
        }
        setDeck(shufled);

    }
    public Card extractOneCard(){
        if(cardsLeft>0){
            Card card=deck.get(0);
            deck.remove(0);
            this.cardsLeft--;
            return card;
        }else{
            System.out.println("Deck empty");
        }
        return null;
    }
    public List<Card> extractCards(int number){
        List<Card> cards=new ArrayList<>();
        if(number>cardsLeft)number=cardsLeft;
        for (int i = 0; i < number; i++) {
            cards.add(deck.get(0));
            deck.remove(0);
            cardsLeft--;
        }
        return cards;
    }
}