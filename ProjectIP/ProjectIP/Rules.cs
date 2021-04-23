using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectIP
{
    abstract class Rules
    {    
        private static int handWinner;              //var folosita pt a vedea cine dintre 2 playeri castiga mana
        private static int maxCard;
        private static int nrPlayers;
        private static int warParties;
        private static int candidate;
        public Rules()
        { }

        public static void setHandWinner(int win)
        {
            handWinner = win;
        }

        public static int getHandWinner()
        {
            return handWinner;
        }

       
        public static void setMaxCard(int maxim)
        {
            maxCard = maxim;
        }
        public static int getMaxCard()
        {
            return maxCard;
        }



        public static void setNrPlayers(int players)
        {
            nrPlayers = players;
        }
        public static int getNrPlayers()
        {
            return nrPlayers;
        }



        public static void setWarParties(int parties)
        {
            warParties = parties;
        }
        public static int getWarParties()
        {
            return warParties;
        }

        public static int getCandidate()
        {
            return candidate;
        }
        /* */
        public static void setCandidate(int candidateName)
        { candidate = candidateName; }
    }
}
