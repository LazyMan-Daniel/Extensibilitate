using System;
using System.Collections.Generic;

namespace ProjectIP
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine();

          Deck deck = new DefaultDeck(52);
            //deck.printDeck();
        deck.shuffleDeck();

          //  Card carte4 = new Card(4, CardColor.club); //construim cartile
          //  Card carte5 = new Card(8, CardColor.club);
           /* var map = new Dictionary<Card, int>(); //facem un map pentru carte si Id jucator
            map.Add(carte4, 13);  //adaugam in map cartea
            map.Add(carte5, 12);
            WarRules.compareCards(map);  //o trimitem la razboi
           */
            deck.printDeck();
            List<Card> returned = deck.extractNCards(5);
            foreach (Card c in returned) Console.WriteLine(c);
            deck.printDeck();

            //teste pt rules
            Card carte4 = new Card(4, CardColor.club);
            Card carte5 = new Card(8, CardColor.club);

            WarRules.compareCards(carte4, carte5);

            int x = WarRules.getHandWinner();
            int y = WarRules.getMaxCard();
            int z = WarRules.getNrPlayers();
            int t = WarRules.getWarParties();

            Console.WriteLine("au jucat " + z + " playeri");
            Console.WriteLine("cea mai mare carte a fost " + y);
            Console.WriteLine("a castigat sigur playerul cu indicele " + x);
            Console.WriteLine("au intrat in razboi " + t);

            Console.WriteLine();

            Card carte1 = new Card(9, CardColor.spade);
            Card carte2 = new Card(9, CardColor.club);
            Card carte3 = new Card(9, CardColor.heart);


            WarRules.compareCards(carte1, carte2, carte5, carte3);

            x = WarRules.getHandWinner();
            y = WarRules.getMaxCard();
            z = WarRules.getNrPlayers();
            t = WarRules.getWarParties();

            Console.WriteLine("au jucat " + z + " playeri");
            Console.WriteLine("cea mai mare carte a fost " + y);
            Console.WriteLine("a castigat sigur playerul cu indicele " + x);
            Console.WriteLine("au intrat in razboi " + t); 
        }
    }
}
