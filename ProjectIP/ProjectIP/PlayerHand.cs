using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectIP
{
    class PlayerHand
    {
       private int initialNumber; //numarul de carti pe care le primeste Player-ul la inceput
       private List<Card> playersCards; // cartile din mana Player-ului
       private bool isTurn; //true cand e randul jucatorului
    

       public void setInitialNumber(int initialNumber) //il modificam in functie de tipul jocului
        { this.initialNumber = initialNumber; }
       public int getInitialNumber() => initialNumber;

       public bool getTurn() => isTurn;

        public int getNrCards() => playersCards.Count;

       public void setTurn() => isTurn = false; //default e false, devine true in timpul jocului

        /*  public void drawInitialCards(Deck deck){ //ar trebui pus in game
                //impartirea cartilor la inceput
                int initialNumber = getInitialNumber();

                List<Card> playersCards = new List<Card>();
                for (int i = 0; i < initialNumber; i++){
                    Random rnd = new Random();
                    List<Card> deckList = deck.getDeck();
                    int index = rnd.Next(deckList.Count);
                    playersCards.Add(deckList[index]); //adauga in mana jucatorului cartea de pe poz index din pachet

                    deckList.RemoveAt(index);
                    deck.setDeck(deckList);
                    }
                } */

        //jucatorul trebuie sa ridice o carte sau mai multe de pe masa
        //jucatorul ia toate cartile de pe masa(deck, deck.Count)
        public void drawCard(Deck deck, int nrCards) //tot in game
            {
            //deck ul de pe table ar treb sa fie tip stack
                Random rnd = new Random();
                List<Card> deckList = deck.getDeck();
                int index = rnd.Next(deckList.Count);
                playersCards.Add(deckList[index]); //adauga in mana jucatorului cartea de pe poz index din pachet
                deckList.RemoveAt(index);

                deck.setDeck(deckList);
            }

            
            // jucatorul treb sa pune jos o carte (la razboi, index=0) 
            //trebuie sa returneze si id ul jucatorului 
            public Card putCard(int index)  
            {
                //ar trebui sa puna o carte jos in functie de reguli
                Card chosenCard = playersCards[index];
               // CardColor color = chosenCard.getColor();
               // int number = chosenCard.getNumber();
               // Console.WriteLine("Number= " + number + ",  culoare= " + color);
                //apeleaza functia de reguli cu number si color ca parametri; daca valideaza ca fiind corect pune cartea jos
                //altfel trimite un mesaj ca trebuie sa alegem alta carte

               // List<Card> deckList = deck.getDeck();
                // deckList.Add(chosenCard);
              //  deck.setDeck(deckList);
                playersCards.RemoveAt(index);

            return chosenCard;
                //Console.WriteLine("Number of cards: " + playersCards.Count);

            }

            public void printCards()
            {
                Console.WriteLine(playersCards.ToString());
            }
        
    }
}
