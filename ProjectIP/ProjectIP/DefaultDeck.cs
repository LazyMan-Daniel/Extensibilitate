using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectIP
{
    class DefaultDeck : Deck
    {
        public DefaultDeck()
        {
            deck = new List<Card>();
            numberOfCards = 0;
        }
        public DefaultDeck(int nrCards)
        {
            deck = new List<Card>();
            int j = 0;
            int number = 2;
            while (j < nrCards)
            {
                foreach (int color in Enum.GetValues(typeof(CardColor)))
                {
                    Card card = new Card(number, (CardColor)color);
                    deck.Add(card);
                    j++;
                    if (j >= nrCards) break;
                }
                number++;
                if (number > 14) number = 2;

            }
            numberOfCards = nrCards;
        }
    }
}
