package classes.joueur;

import classes.Part;
import classes.carte.Carte;
import classes.carte.Parameters;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurVirtuel extends Joueur implements Strategie {

    private Strategie strategie;

    private int nbrjoueur;

    public void JoueurVirtuel() {

    }


    @Override
    public void jouer() {

        strategie.jouer();

        Parameters para = new Parameters();
        para.setMyself(this);

        if(this.getCarteMain().size() >= 7){
            this.getCarteMain().remove(0);
            this.getCarteMain().remove(1);
            this.getCarteMain().remove(0);
        }else{
            while(this.getCarteMain().size()<7){
                this.piocher(Part.getPart().piocher1Carte());
            }
        }
    }

    @Override
    public int jouer(Parameters parameters) {
        //suijixuanze
        return 0;
    }

    //////carte croyants yong
    @Override
    public int jouer(List<Carte> cartemain) {
        return 0;
    }

    @Override
    public void jouer(PointAction PointAct, List<Carte> cartemain) {

    }
    public int jouer(int size){ return 0;}
    public List<Joueur> jouer(int size,List<Joueur>listjoueur){return listjoueur;}

    public void afficherfalse(){
    }
}
