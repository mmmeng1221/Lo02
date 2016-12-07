package classes.joueur;

import classes.carte.Carte;
import classes.joueur.PointAction;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface Strategie {
    public void jouer(PointAction PointAct, List<Carte> cartemain);
}
