public class Card {

    private int number, color;

    public Card(int number, int color) {
        if(number < 2 || number > 14) {
            System.out.println("Invalid number");
        }
        else this.number = number;

        if(color < 1 || color > 4){
            System.out.println("Invalid color");
        }
        else this.color = color;
    }

    public void setNumber(int number) {
        if(number < 2 || number > 14) {
            System.out.println("Invalid number");
        }
        else this.number = number;
    }

    public void setColor(int color) {
        if(color < 1 || color > 4){
            System.out.println("Invalid color");
        }
        else this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", color=" + color +
                '}';
    }


}
