

public class Main {

        public static void main(String[] args) {
            PlayerHand p1 = new PlayerHand();
            p1.makeDeck();
            p1.setInitialNumber(5);
            p1.drawInitialCards();
            p1.printCards();
            p1.drawCard();
            p1.printCards();
            p1.putCard(3);
            p1.printCards();
            p1.setTurn();
            System.out.println(p1.getTurn());
        }

        /*
        public static void main(String[] args) {
	Deck deck=new Deck();
	deck.printDeck();
	deck.shuffleDeck();
	deck.printDeck();
    }
         */

}
