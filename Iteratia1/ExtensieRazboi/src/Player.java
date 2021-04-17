public class Player {
    private String name;
    private int id, index;
    private boolean isOutOfTheGame = false;
    private PlayerHand manaJucator;
    private int cardColors[] = new int[256];
    private boolean isCardPutOnTheTable[][] = new boolean[256][256];
    private int currentNumberOfCards;
    private boolean CurrentlyPlaying;
    private int valueChosen;
    private int ColorChosen;
    private int numberOfPutCards = 0;
    private int currentValueToBePut;

    Player(int nrCartiPlayer, Deck carti) {
        manaJucator=new PlayerHand(nrCartiPlayer, carti);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOutOfTheGame() {
        return isOutOfTheGame;
    }

    public void setOutOfTheGame(boolean outOfTheGame) {
        isOutOfTheGame = outOfTheGame;
    }
/*
    public int[] getCards() {
        return cards;
    }

    public void setCards(int[] cards) {
        this.cards = cards;
    }

    public int[] getCardColors() {
        return cardColors;
    }

    public void setCardColors(int[] cardColors) {
        this.cardColors = cardColors;
    }

    public boolean[][] getIsCardPutOnTheTable() {
        return isCardPutOnTheTable;
    }

    public void setIsCardPutOnTheTable(boolean[][] isCardPutOnTheTable) {
        this.isCardPutOnTheTable = isCardPutOnTheTable;
    }

    public void setIsCardPutOnTheTable(int index, int color, boolean isCardPutOnTheTable) {
        this.isCardPutOnTheTable[index][color] = isCardPutOnTheTable;
    }

    public int getCurrentNumberOfCards() {
        return currentNumberOfCards;
    }

    public void setCurrentNumberOfCards(int currentNumberOfCards) {
        this.currentNumberOfCards = currentNumberOfCards;
    }

    public boolean getCurrentlyPlaying() {
        return CurrentlyPlaying;
    }

    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        CurrentlyPlaying = currentlyPlaying;
    }

    public boolean isCurrentlyPlaying() {
        return CurrentlyPlaying;
    }

    public int getValueChosen() {
        return valueChosen;
    }

    public void setValueChosen(int valueChosen) {
        this.valueChosen = valueChosen;
        if (valueChosen <= 10)
            this.numberOfPutCards = valueChosen;
        else if (valueChosen == 13)
            this.numberOfPutCards = 11;
        else
            this.numberOfPutCards = valueChosen + 1;
        if (currentNumberOfCards < this.numberOfPutCards)
            this.numberOfPutCards = currentNumberOfCards;
    }

    public int getColorChosen() {
        return ColorChosen;
    }

    public void setColorChosen(int colorChosen) {
        ColorChosen = colorChosen;
    }

    public int getNumberOfPutCards() {
        return numberOfPutCards;
    }

    public void setNumberOfPutCards(int numberOfPutCards) {
        this.numberOfPutCards = numberOfPutCards;
    }

    public int getCurrentValueToBePut() {
        return currentValueToBePut;
    }

    public void setCurrentValueToBePut(int currentValueToBePut) {
        this.currentValueToBePut = currentValueToBePut;
    }*/

}