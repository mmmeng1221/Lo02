package VueClasse;

import classes.Part;
import classes.joueur.Joueur;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;

import javax.swing.*;
import java.util.List;
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
        this.nom = nom;
       /* String str =  nom + " : " + "\n"+
                "Jour :" + MonNbrJour +"\n" + "Nuit :" +
                MonNbrNuit + "\n" +"Neant :" + "\n" + MonNbrNeant +
                "Mes points de prières :" + MonNbrCroyant;*/
        String str1 = new String(nom +
                " Jour : " + MonNbrJour);
        String str2 = new String("Nuit :" + MonNbrNuit);
        String str3 = new String("Neant :" + MonNbrNeant );
        String str4 = new String("Mes points de prières :" + MonNbrCroyant);
        String str = "<html>" + str1 +"<br>" + str2 + "<br>" + str3 + "<br>" + str4 + "<html>";
        super.setText(str);
    }
    @Override
    public void update(Observable arg, Object b) {


       if(arg instanceof Joueur)
        {
            Joueur joueur = (Joueur)arg;
            MonNbrJour = joueur.getPointActTot().getJour();
            MonNbrNuit = joueur.getPointActTot().getNuit();
            MonNbrNeant = joueur.getPointActTot().getNeant();

            String str1 = new String(nom +
                    " Jour : " + MonNbrJour);
            String str2 = new String("Nuit :" + MonNbrNuit);
            String str3 = new String("Neant :" + MonNbrNeant );
            String str4 = new String("Mes points de prières :" + MonNbrCroyant);
            String str = "<html>" + str1 +"<br>" + str2 + "<br>" + str3 + "<br>" + str4 + "<html>";

            setText(str);
        }

            /*List<Joueur> joueurlist = (List) ((Part) arg).getListeJouCourant();
            int i =1;
            for(i=0;i<=joueurlist.size()-2;i++){
                String str =  new String(
                    "Joueur Virtuel" + i+1);
                super.setText(str);
                MonNbrJour = joueurlist.get(i).getPointActTot().getJour();
                MonNbrNuit = joueurlist.get(i).getPointActTot().getNuit();
                MonNbrNeant = joueurlist.get(i).getPointActTot().getNeant();
                MonNbrCroyant = joueurlist.get(i).getNbrCro();
                String str1 =  new String(
                        "Jour " + MonNbrJour +"\n" + "Nuit " + MonNbrJour + "\n" +"Neant" + MonNbrNeant+"\n");
                super.setText(str1);
            }
            String str =  new String(
                    "Moi" );
            super.setText(str);
            MonNbrJour = joueurlist.get(joueurlist.size()-1).getPointActTot().getJour();
            MonNbrNuit = joueurlist.get(joueurlist.size()-1).getPointActTot().getNuit();
            MonNbrNeant = joueurlist.get(joueurlist.size()-1).getPointActTot().getNeant();
            MonNbrCroyant = joueurlist.get(joueurlist.size()-1).getNbrCro();
            String str1 =  new String(
                    "Jour " + MonNbrJour +"\n" + "Nuit " + MonNbrJour + "\n" +"Neant" + MonNbrNeant);
            super.setText(str1);/*




        }*/
        else
            System.out.println("erreur");
    }
}
