using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
namespace ProjectIP
{
    abstract class Game
    {
        protected List<Player> players;// primeste o lista de jucatori ; players.Count va fi nr de jucatori
        protected Deck deck;
        protected int minPlayers; // nr minim de jucatori
        protected int maxPlayers; // nr maxim de jucatori
        public Game()
        {
            this.setMinPlayers(2);
        }
        public void setPlayers(List<Player> players)
        { this.players = players; }

        public List<Player> getPlayers()
        { return players; }

        public void setDeck(Deck deck)
        { this.deck = deck; }

        public Deck getDeck()
        { return this.deck; }
        public void setMinPlayers(int minPlayers)
        { this.minPlayers = minPlayers; }
        public int getMinPlayers()
        { return this.minPlayers; }
        public void setMaxPlayers(int maxPlayers)
        { this.maxPlayers = maxPlayers; }
        public int getMaxPlayers()
        { return this.maxPlayers; }
        public void addPlayer(Player player)
        {
            this.players.Add(player);
        }
        //pentru a elimina un jucator, avem disponibila metoda RemoveAt ; pentru al i-lea player, avem ElementAt
        
        public int GetNrPlayers()
        {
            return players.Count;
        }
    }
}
