package classes.joueur;

import classes.De;
import classes.Part;
import classes.carte.Carte;
import classes.carte.Parameters;

import javax.swing.JOptionPane;

import java.util.List;

/**
 * Created by zhangmeng on 08/12/2016.
 */
public class EasyStrategy implements Strategie{
    @Override
    public void jouer(Parameters parameters) {
        //Part.getPart().JoueurAjouterPoint(De.getDe().lancer());
        if(parameters.getMyself().getCarteMain().size() >= 7){
            parameters.getMyself().getCarteMain().remove(0);
            parameters.getMyself().getCarteMain().remove(1);
            parameters.getMyself().getCarteMain().remove(0);

            JOptionPane.showMessageDialog(null, "il a déffausser 3 cartes", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
            System.out.println(this + "a déffausser 3 cartes");
        }else{
            while(parameters.getMyself().getCarteMain().size()<7){
                parameters.getMyself().piocher(Part.getPart().piocher1Carte());
            }
            JOptionPane.showMessageDialog(null, "il a pioché des cartes.", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
            System.out.println(this + "a pioché des cartes.");
        }
    }
}
