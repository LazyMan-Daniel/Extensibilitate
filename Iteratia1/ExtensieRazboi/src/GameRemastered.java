import java.util.ArrayList;
import java.util.List;

public class GameRemastered {

    //52 carti: max 52 playeri
    private List<Player> jucatori;
    private int nrCarti;
    private int nrCartiPlayer;
    private boolean existaPrizonier;
    private Deck carti;

    public GameRemastered(int nrPlayers){
        this.nrCartiPlayer=52/nrPlayers;
        this.existaPrizonier=false;
        carti=new Deck();
        carti.shuffleDeck();
        jucatori=new ArrayList<>();
        //52->impartit la 4 jucatori-> 13carti pe jucator
        for(int i=0;i<nrPlayers;i++){
            jucatori.add(new Player(nrCartiPlayer,carti));
        }
    }

    public playGame(){
        //daca exista carte prizonier
            //daca nu-> jocul simplu + castigatorul alege cartea prizonier
            //daca da-> timer-ul (alege daca pui prizonier sau nu)
                      // caz de egalitate (tot cu timer)
                      // castigatorul alege cartea prizonier
    }


}
