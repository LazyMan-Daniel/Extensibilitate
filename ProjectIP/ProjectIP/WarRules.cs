using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

//pt extensie, sa puneti regulile in plus, aici aveti niste reguli basic

namespace ProjectIP
{
    class WarRules : Rules
    {

        public static void compare2Cards(Card card1, Card card2)//metoda compara cartile puse de 2 playeri
        {
            int value1, value2;
            value1 = card1.getNumber();
            value2 = card2.getNumber();

            if (value1 > value2)
            {
                setCandidate(1);        //a castigat primul

            }
            else if (value2 > value1)
            {
                setCandidate (2);       // a castigat al doilea
            }
            else
            {
                setCandidate(0);     //e egalitate
            }

        }


        public static void compareCards(params Card[] number)
        {
          /*  var map = new Dictionary<Card , int>();
            map.Add("cat", "orange");
            //Console.Write(map);
            foreach (var pair in map)
            {
                string key = pair.Key;
                string value = pair.Value;
                Console.WriteLine(key + "/" + value);
            }
            string result = map["cat"];
            Console.WriteLine(result); */


            setHandWinner(0);
            setNrPlayers(number.Length);                 //cati playeri pun carte jos
            setWarParties(0);                             //nimeni nu e la razboi...inca
            setMaxCard(-1);                               //indicele celei mai mare carti
            int maxValue = -1;                            //cea mai mare carte curenta
            int count = 1;                              // indicele playerului, player1, player2 etc
            int[] vector = new int[getNrPlayers()+ 1];          //aici punem val cartilor playerilor
            for (int i = 0; i < number.Length; i++)
            {
                int x = number[i].getNumber();          //retine cartea playerului in vector
                vector[count] = x;

                if (vector[count] >= maxValue)          // determina cea mai mare carte
                {
                    if (vector[count] == maxValue)       // determina daca va fi razboi
                    {
                        setWarParties(getWarParties()+1);                   //daca mai multi playeri au cele mai mari carti, se da razboi
                        setHandWinner(0);                 //nu exista un castigator imediat
                    }

                    else                                //daca s a gasit o carte mai mare, nu se mai tine razboi
                    {
                        maxValue = vector[count];
                        setWarParties(1);
                        setHandWinner(count);            // playerul cu indicele count e cel mai probabil sa castige
                    }
                }

                count++;                                //pregatim pozitia pt urmatoarea carte
            }

            setMaxCard(maxValue);                         //am determinat cea mai mare carte la momentul actual

            if (getWarParties() > 1)                          //daca e razboi, retinem cumva indicele playerilor implicati
            {
                setHandWinner(0);
                for (int i = 1; i < count; i++)             //formeaza un nr din indicii playerilor care au inceput razboiul
                {                                       //e ora 3, nu m am putut gandi la altceva
                    if (vector[i] == getMaxCard())
                        setHandWinner( getHandWinner() * 10 + i);
                }
            }

        }


        public static void war(Dictionary<Stack<Card>, Player> warCards, DefaultDeck winnerDeck)
        {
            /// 1. hotaram cate carti pune fiecare jucator (
            ///      -daca are 0 carti-aia e;
            ///      -daca are nr carti '<=' nr carti de pus jos -nr carti;
            ///      -daca are nr carti '>' nr carti de pus jos -nr carti de pus jos;
            /// 2. pargurgem stack urile, gasim val max, aflam cati au carte max
            ///       -> ori intram iar in war cu jucatorii ramasi
            ///       -> ori alegem castigatorul/castigatorii(in caz de este egalitate si au 0 carti in mana)
            ///       



        }


        public static void playSimple(Game game) 
        {
            int nrOfPlayers = game.GetNrPlayers();
            var players = game.getPlayers();
            //var map = new Dictionary<Card, int>();
            var tableCards = new Dictionary<Card, Player>();

            bool GameOver = false;

            while (!GameOver)
            {
                foreach(Player player in players) 
                {
                    /// 1. Jucatorul pune carte jos
                    /// 2. Din mana jucatorului se 'scoate' acea carte
                    /// 3. Cartile se adauga in map-ul cartiMasa<Carte, ID>
                    
                    for (int i = 0; i < tableCards.Count; i++)
                    {
                        tableCards.Add(player.getPlayerHand().putCard(0), player);
                    }

                }

                /// 4. Se lucreaza cu map-ul
                /// 5. Se gaseste val max si nr de jucatori  cu val max

                int valMax = 0, nrPlayers = 0;
                foreach (var card in tableCards)
                {   
                    if (card.Key.getNumber() == valMax)  //daca se gaseste val max, creste nr playeri
                    {
                        nrOfPlayers++;
                    }

                    if (card.Key.getNumber() > valMax)  //daca se gaseste val max, se initializeaza val max
                    {
                        valMax = card.Key.getNumber();
                        nrOfPlayers = 1;
                    }

                }

                if (nrOfPlayers == 1)  //daca avem un singur jucator cu carte maxima
                {
                    foreach (var card in tableCards)
                        if (card.Key.getNumber() == valMax)  //gasim jucatorul si il afisam
                        {
                            Console.WriteLine("Jucatorul " + card.Value.getId());
                            //treb sa i dam si cartile 
                            break;
                        }
                }

                else   //intra in razboi nr playeri
                {
                    var warCards = new Dictionary<Stack<Card>, Player>();

                    foreach (var card in tableCards)
                    {  //pentru fiecare jucator care intra in war, construim un stack
                       // sa stim care e ultima carte pusa jos de el
                        if (card.Key.getNumber() == valMax)
                        {
                            Stack<Card> cardsStack = new Stack<Card>();
                            cardsStack.Push(card.Key); //adaugam ultima carte intr-ul stack

                            warCards.Add(cardsStack, card.Value);
                        }

                        else  //pt jucatorii din afara war-ului
                        {
                            if (card.Value.getPlayerHand().getNrCards() == 0) //daca jucatorii care n-au intrat in war
                                                                              //raman cu 0 carti, atunci AFARA
                            {
                                players.Remove(card.Value);
                            }
                        }

                    }
                    DefaultDeck winnerDeck = new DefaultDeck(); //vom salva toate cartile de pe masa intr-un deck

                    foreach (var card in tableCards) {  //aceste carti vor fi atribuite castigatorului
                        winnerDeck.addCard(card.Key);
                    }

                    tableCards.Clear(); //golim memoria la tableCards, sa nu apara erori

                    war(warCards,winnerDeck);

                }   

            }


        }




        /* pt razboi
         * 
         * 
         * public static void cardsWar(int maxCard,params PlayerHand[] number)


         {     avem nrPlayers si pt fiecare trebuie creat cate un deck
                 vedem daca deck urile are marimea minima
               tragem a maxCard carte din fiecare deck, le comparam intre ele, cu functia compare 2 cards( prima cu a 2 a, apoi maximul cu a 3 a ,etc) 
               cea mai mare carte castiga toate cartile pana la a maxCard carte din fiecare pachet
                 daca exista din nou egalitate intre 2 carti - e ok daca nu sunt cele mai mari
                                                             - daca sunt cele mai mari se incepe din nou razboiul



          }

         */




    }
}

