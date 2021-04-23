using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;

namespace ProjectIP
{
    abstract class Deck
    {
        protected List<Card> deck;//primeste o lista de carti
        protected int numberOfCards;// nr de carti pe care le primeste


        public Deck()//constructorul pt deck ul gol
        {
            numberOfCards = 0;
            deck = new List<Card>();
        }


        public void addCard(Card card)
        {
            deck.Add(card);
            numberOfCards++;
        }
        public List<Card> getDeck()
        {
            return deck;
        }
        public void setDeck(List<Card> card)
        {
            deck = card;
            numberOfCards = card.Count;

        }
        public void setNumberOfCards(int numberOfCards)
        { this.numberOfCards = numberOfCards; }
        public int getNumberOfCards()
        { return this.numberOfCards; }
        public void setDeck(Deck deck2)
        {
            this.deck = deck2.deck;
            this.numberOfCards = deck2.numberOfCards;
        }

        public void shuffleDeck()
        {
            var random = new Random();
            int nrCardShuffled, position;
            for (int i = 0; i < 50; i++)
            {

                nrCardShuffled = random.Next(1, numberOfCards - 1);
                position = random.Next(nrCardShuffled, numberOfCards);
                if (position % 5 == 0) { position = numberOfCards; }
                rotateCards(nrCardShuffled, position);
            }
        }
        private void rotateCards(int nrCards, int pos)
        {
            List<Card> shufled = new List<Card>();
            int i;
            for (i = nrCards; i < pos; i++)
            {
                shufled.Add(deck[i]);
            }
            for (i = 0; i < nrCards; i++)
            {
                shufled.Add(deck[i]);
            }
            for (i = pos; i < this.numberOfCards; i++)
            {
                shufled.Add(deck[i]);
            }
            setDeck(shufled);
        }
        public void printDeck()
        {
            foreach (Card card in deck)
            {
                Console.Write(card + ",");
            }
            Console.WriteLine();
        }
        public Card extractOneCard()
        {
            Card card = deck[0];
            deck.RemoveAt(0);
            return card;

        }
        public List<Card> extractNCards(int n)
        {
            List<Card> cards = new List<Card>();
            if (n > numberOfCards) n = numberOfCards;
            for(int i = 0; i < n; i++)
            {
                cards.Add(deck[0]);
                deck.RemoveAt(0);
            }
            return cards;
        }

    }
}
