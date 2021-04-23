using System;
using System.Collections.Generic;
using System.Text;
public enum CardColor//enumul se apeleaza cu CardColor.una din cele 4 valori
{ club, diamond, heart, spade }
namespace ProjectIP
{
    class Card
    {
        private int number;
        private CardColor color;

        //constructor pt carte
        public Card(int number, CardColor color)
        {
            if (number >= 2 && number <= 14)
            {
                this.number = number;
                this.color = color;
            }
            else Console.WriteLine("Invalid card");

        }
        public int getNumber()
        {
            return this.number;
        }
        public void setNumber(int number)
        {
            this.number = number;
        }
        public CardColor getColor()
        {
            return this.color;
        }
        public void setColor(CardColor color)
        {
            this.color = color;
        }
        public override string ToString()
        {
            return "{" + number + "," + color + "}";
        }

    }
}
