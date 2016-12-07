package classes.joueur;

import classes.carte.Carte;
import classes.carte.Parameters;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurVirtuel extends Joueur implements Strategie {


    private static int nbrjoueur;

    public void JoueurVirtuel() {

    }


    @Override
    public void jouer() {
        //suijixuanze
    }

    @Override
    public int jouer(Parameters parameters) {
        //suijixuanze
        return 0;
    }

    public static int getNbrjoueur() {
        return nbrjoueur;
    }

    public static void setNbrjoueur(int nbrjoueur) {
        JoueurVirtuel.nbrjoueur = nbrjoueur;
    }

    @Override
    public int jouer(List<Carte> cartemain) {
        return 0;
    }

    @Override
    public void jouer(PointAction PointAct, List<Carte> cartemain) {

    }
}
