package classes.joueur;

import classes.Part;
import classes.carte.Carte;
import classes.carte.Parameters;

import java.util.List;

/**
 * Created by zhangmeng on 08/12/2016.
 */
public class EasyStrategy implements Strategie{
    @Override
    public void jouer(Parameters parameters) {
        if(parameters.getMyself().getCarteMain().size() >= 7){
            parameters.getMyself().getCarteMain().remove(0);
            parameters.getMyself().getCarteMain().remove(1);
            parameters.getMyself().getCarteMain().remove(0);
        }else{
            while(parameters.getMyself().getCarteMain().size()<7){
                parameters.getMyself().piocher(Part.getPart().piocher1Carte());
            }
        }
    }
}
