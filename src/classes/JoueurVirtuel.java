package classes;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurVirtuel extends Joueur {


    private static int nbrjoueur;
    public void JoueurVirtuel(){

    }
    @Override
    public int jouer(Parameters parameters){
        //suijixuanze

    }
    public static int getNbrjoueur() {
        return nbrjoueur;
    }

    public static void setNbrjoueur(int nbrjoueur) {
        JoueurVirtuel.nbrjoueur = nbrjoueur;
    }
}
