
import java.util.Arrays;

public class Game {
    private Player players[] = new Player[256];
    private int numberOfPlayers, numberOfRounds = 0, numberOfPlayersCurrentlyPlaying, numberOfFoundWinners, numberOfCardsOnTheTable;
    private Player roundWinners[] = new Player[256];
    private int cardsOnTheTable[] = new int[256];
    private int colorsOfCardsOnTheTable[] = new int[256];
    private boolean foundDrawn = false;
    private int currentlyPlayingPlayersIndexes[] = new int[256];
    private int cardsPutPerPlayer[][] = new int[256][256];
    private int numberOfCardsPutPerPlayer[] = new int[256];
    private int valueForCurrentGame[] = new int[256];
    private int colorForCurrentGame[] = new int[256];
    private boolean gameEnded = false;
    private Player winner;

    Game() {
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public int getNumberOfCardsOnTheTable() {
        return numberOfCardsOnTheTable;
    }

    public void setNumberOfCardsOnTheTable(int numberOfCardsOnTheTable) {
        this.numberOfCardsOnTheTable = numberOfCardsOnTheTable;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public Player[] getRoundWinners() {
        return roundWinners;
    }

    public void setRoundWinners(Player[] roundWinners) {
        this.roundWinners = roundWinners;
    }

    public int[] getCardsOnTheTable() {
        return cardsOnTheTable;
    }

    public void setCardsOnTheTable(int[] cardsOnTheTable) {
        this.cardsOnTheTable = cardsOnTheTable;
    }

    public int[] getColorsOfCardsOnTheTable() {
        return colorsOfCardsOnTheTable;
    }

    public void setColorsOfCardsOnTheTable(int[] colorsOfCardsOnTheTable) {
        this.colorsOfCardsOnTheTable = colorsOfCardsOnTheTable;
    }

    public boolean isFoundDrawn() {
        return foundDrawn;
    }

    public void setFoundDrawn(boolean foundDrawn) {
        this.foundDrawn = foundDrawn;
    }

    public int[] getCurrentlyPlayingPlayersIndexes() {
        return currentlyPlayingPlayersIndexes;
    }

    public void setCurrentlyPlayingPlayersIndexes(int[] currentlyPlayingPlayersIndexes) {
        this.currentlyPlayingPlayersIndexes = currentlyPlayingPlayersIndexes;
    }

    public int getNumberOfPlayersCurrentlyPlaying() {
        return numberOfPlayersCurrentlyPlaying;
    }

    public void setNumberOfPlayersCurrentlyPlaying(int numberOfPlayersCurrentlyPlaying) {
        this.numberOfPlayersCurrentlyPlaying = numberOfPlayersCurrentlyPlaying;
    }

    public int getNumberOfFoundWinners() {
        return numberOfFoundWinners;
    }

    public void setNumberOfFoundWinners(int numberOfFoundWinners) {
        this.numberOfFoundWinners = numberOfFoundWinners;
    }

    public int[][] getCardsPutPerPlayer() {
        return cardsPutPerPlayer;
    }

    public void setCardsPutPerPlayer(int[][] cardsPutPerPlayer) {
        this.cardsPutPerPlayer = cardsPutPerPlayer;
    }

    public int[] getNumberOfCardsPutPerPlayer() {
        return numberOfCardsPutPerPlayer;
    }

    public void setNumberOfCardsPutPerPlayer(int[] numberOfCardsPutPerPlayer) {
        this.numberOfCardsPutPerPlayer = numberOfCardsPutPerPlayer;
    }

    public int[] getValueForCurrentGame() {
        return valueForCurrentGame;
    }

    public void setValueForCurrentGame(int[] valueForCurrentGame) {
        this.valueForCurrentGame = valueForCurrentGame;
    }

    public int[] getColorForCurrentGame() {
        return colorForCurrentGame;
    }

    public void setColorForCurrentGame(int[] colorForCurrentGame) {
        this.colorForCurrentGame = colorForCurrentGame;
    }

    public void removeArrayElement(int array[], int dimension, int index) {
        for (int i = index; i < dimension - 1; ++i)
            array[i] = array[i + 1];
    }

    public void removeArrayElements(int array[], int dimension, int index1, int index2) {
        int k = index2;
        int i = index1;
        int j = index2;
        int p = 0;
        while (i + j + p < dimension) {
            array[i + p] = array[i + j + p];
            p++;
        }
    }

    public void playRound(int roundIndex) {
        int k = 0, maxCardValue = -1, numberOfFoundWinners = 0, p = 0;
        int usefulArray[] = new int[256];
        int usefulArrayColor[] = new int[256];
        for (int i = 0; i < numberOfPlayers; ++i)
            if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                cardsOnTheTable[k] = players[i].getCards()[0];
                colorsOfCardsOnTheTable[k++] = players[i].getCardColors()[0];
                players[i].setIsCardPutOnTheTable(players[i].getCards()[0], players[i].getCardColors()[0], true);
                if (maxCardValue < players[i].getCards()[0])
                    maxCardValue = players[i].getCards()[0];
            }
        for (int i = 0; i < numberOfPlayers; ++i) {
            if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0 && maxCardValue == players[i].getCards()[0]) {
                ++numberOfFoundWinners;
            }

        }
        if (numberOfFoundWinners == 1) {
            int certainPlayerIndex = 0;
            for (int i = 0; i < numberOfPlayers; ++i)
                if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                    if (maxCardValue == players[i].getCards()[0]) {
                        roundWinners[roundIndex] = players[i];
                        certainPlayerIndex = i;
                    }
                    usefulArray[p] = players[i].getCards()[0];
                    usefulArrayColor[p++] = players[i].getCardColors()[0];
                    removeArrayElement(players[i].getCards(), players[i].getCurrentNumberOfCards(), 0);
                    removeArrayElement(players[i].getCardColors(), players[i].getCurrentNumberOfCards(), 0);
                    players[i].setCurrentNumberOfCards(players[i].getCurrentNumberOfCards() - 1);
                }
            for (int i = 0; i < p; ++i) {
                players[certainPlayerIndex].getCards()[i + players[i].getCurrentNumberOfCards()] = usefulArray[i];
                players[certainPlayerIndex].getCardColors()[i + players[i].getCurrentNumberOfCards()] = usefulArrayColor[i];
            }
            players[certainPlayerIndex].setCurrentNumberOfCards(players[certainPlayerIndex].getCurrentNumberOfCards() + p);
            setNumberOfCardsOnTheTable(0);
        } else {
            int winnerIndex = 0;
            for (int i = 0; i < numberOfPlayers; ++i)
                if (maxCardValue == players[i].getCards()[0] && players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                    {
                        currentlyPlayingPlayersIndexes[k++] = i;
                        players[i].setCurrentlyPlaying(true);
                        players[i].setValueChosen(maxCardValue);
                    } // eventual si else setcurrentlyplaying(false);
                } else
                    players[i].setCurrentlyPlaying(false);
            while (numberOfFoundWinners != 1) {
                int max = -1;
                //int winnerNumber=0;
                numberOfFoundWinners = 0;
                for (int i = 0; i < numberOfPlayers; ++i)
                    if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                        for (int j = 0; j < players[i].getNumberOfPutCards(); ++i) {
                            cardsOnTheTable[k] = players[i].getCards()[j];
                            colorsOfCardsOnTheTable[k++] = players[i].getCardColors()[j];
                            players[i].setIsCardPutOnTheTable(players[i].getCards()[j], players[i].getCardColors()[j], true);
                            if (j == players[i].getNumberOfPutCards())
                                players[i].setCurrentValueToBePut(players[i].getCards()[j]);
                            // eventual afisam valoarea extrasa si vizibila - current value to be put
                        }
                        removeArrayElements(players[i].getCards(), players[i].getCurrentNumberOfCards(), 0, players[i].getNumberOfPutCards());
                        removeArrayElements(players[i].getCardColors(), players[i].getCurrentNumberOfCards(), 0, players[i].getNumberOfPutCards());
                    }
                for (int i = 0; i < numberOfPlayers; ++i)
                    if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                        if (players[i].getCurrentValueToBePut() > max)
                            max = players[i].getCurrentValueToBePut();
                    }
                for (int i = 0; i < numberOfPlayers; ++i)
                    if (players[i].getCurrentlyPlaying() && players[i].getCurrentNumberOfCards() > 0) {
                        if (players[i].getCurrentValueToBePut() == max) {
                            ++numberOfFoundWinners;
                            players[i].setCurrentlyPlaying(true);
                            players[i].setValueChosen(max);
                            winnerIndex = i;
                        } else
                            players[i].setCurrentlyPlaying(false);
                    }

            }
            for (int i = 0; i < numberOfPlayers; ++i)
                if (i == winnerIndex)
                    roundWinners[roundIndex] = players[winnerIndex];
            for (int i = 0; i < k; ++i) {
                players[i].getCards()[players[i].getCurrentNumberOfCards()] = getCardsOnTheTable()[i];
                players[i].getCardColors()[players[i].getCurrentNumberOfCards()] = getColorsOfCardsOnTheTable()[i];
                players[i].setCurrentNumberOfCards(players[i].getCurrentNumberOfCards() + 1);
            }
            setNumberOfCardsOnTheTable(0);
        }
    }

    public void playGame() {
        int numberOfLosingPlayers = 0;
        while (!isGameEnded()) {

            playRound(numberOfRounds++);
            for (int i = 0; i < numberOfPlayers; ++i)
                if (players[i].getCurrentNumberOfCards() == 0)
                    ++numberOfLosingPlayers;
            if (numberOfLosingPlayers == numberOfPlayers - 1)
                setGameEnded(true);
        }
        winner = roundWinners[numberOfRounds - 1];
    }

}

