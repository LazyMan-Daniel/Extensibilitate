using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectIP
{
    class RegularPlayer : Player
    {
        public RegularPlayer(int id, string name, PlayerHand playerHand) : base(id, name, playerHand)
        {
        }

        public void joinTable(String idTable)
        {
            // idTable: va fi id-ul de conectare al mesei create de Game Master
            // TO DO: Conectarea la o masa de joc
        }

    }
}