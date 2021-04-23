using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectIP
{
    class WarGame : Game
    {public WarGame()
        { this.getDeck().setNumberOfCards(52); // setam numarul de carti din pachet in cazul razboiului
            // nr minim, si nr maxim de jucatori -> 2, respectiv 15 :
            this.setMinPlayers(2); 
            this.setMaxPlayers(15);
        }
    }
}
