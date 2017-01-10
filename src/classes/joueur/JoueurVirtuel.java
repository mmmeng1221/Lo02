package classes.joueur;

import classes.carte.Carte;
import classes.carte.Parameters;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurVirtuel extends Joueur {

    private Strategie strategie;

    private int nbrjoueur;


    public JoueurVirtuel(Strategie strategie) {
        this.strategie = strategie;
    }


    @Override
    public void jouer() {

        Parameters para = new Parameters();
        para.setMyself(this);
        strategie.jouer(para);
    }


    //////carte croyants yong
    @Override
    public int jouer(List<Carte> cartemain) {
        return 0;
    }


    public void jouer(PointAction PointAct, List<Carte> cartemain) {

    }
    public int jouer(int size){ return 0;}
    public List<Joueur> jouer(int size,List<Joueur>listjoueur){return listjoueur;}

    public void afficherfalse(){
    }
    public int jouer(Parameters parameters) {return 0;}
}

