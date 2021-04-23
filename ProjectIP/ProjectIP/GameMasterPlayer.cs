using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectIP
{
    class GameMasterPlayer : Player
    {
        public GameMasterPlayer(int id, string name, PlayerHand playerHand) : base(id, name, playerHand)
        {
        }

        public void createTable(int nrMinPlayers, int nrMaxPlayers, String gameName)
        {
            // gameName va fi numele unui joc dintr-o lista predefinita de jocuri
            // TO DO: crearea unui obiect de tip Table pentru a sustine jocul ales
            //        introducerea Game Master-ului la masa creata
        }
    }
}