using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectIP
{
    /* 
     * Player va fi clasa abstracta extinsa atat de Game Master cat si de un jucator obisnuit(deoarece ambii vor putea juca)
     * 
     * 
     */
    abstract class Player
    {
        int id;
        String name;
        PlayerHand playerHand;
       

        protected Player(int id, string name, PlayerHand playerHand)
        {
            this.id = id;
            this.name = name;
            this.playerHand = playerHand;
        }

        public void nextMove()
        {
            // Urmatoarea miscare a jucatorului curent
        }

        public PlayerHand getPlayerHand()
        {
            return playerHand;
        }

        public int getId()
        {
            return id;
        }

    }
}