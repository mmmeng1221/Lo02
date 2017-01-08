package VueClasse;

import classes.Part;
import classes.joueur.Joueur;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2017/1/6.
 */
public class VuePoint extends JLabel implements Observer {

    private int MonNbrJour;
    private int MonNbrNuit;
    private int MonNbrNeant;
    private int MonNbrCroyant;




    private String nom;

    public VuePoint(String nom){
        String str =  new String(
                "Jour " + MonNbrJour +"\n" + "Nuit " + MonNbrJour + "\n" +"Neant" + MonNbrNeant);
        super.setText(str);

    }
    @Override
    public void update(Observable o, Object arg) {


       if(arg instanceof Joueur)
        {
            Joueur joueur = (Joueur)arg;
            MonNbrJour = joueur.getPointActTot().getJour();
            MonNbrNuit = joueur.getPointActTot().getNuit();
            MonNbrNeant = joueur.getPointActTot().getNeant();
            MonNbrCroyant = joueur.getNbrCro();


        }
        else
            System.out.println("erreur");
    }
}
