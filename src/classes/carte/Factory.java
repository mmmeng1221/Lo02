package classes.carte;


import VueClasse.MaVueTotale;
import VueClasse.VueCarte;
import classes.Constants;
import classes.De;
import classes.Part;
import classes.joueur.Joueur;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.zip.CRC32;


/**
 * Created by zhangmeng on 02/12/2016.
 */
public class Factory {
    private static Factory factory = new Factory();

    private Factory() {
    }

    public static Factory getFactory() {
        return factory;
    }

    //Guide

    /**
     * Création des cartes Martyr
     * @param nom
     * @param capacite
     * @param dogs
     * @param origine
     * @param nbCroyant
     * @return
     */
    public Guide createMartyr(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant, Image image) throws IOException {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogs);
        /*File file = new File("Martyr.jpg");

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new Guide(nom, capacite, temp, origine, nbCroyant, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Apocalypse apocalypse = creatApo(null,0);
                apocalypse.sacrifier(parameters);
            }
        },image);
    }

    /**
     * @param nom       le nom de ce carte
     * @param capacite  L'expliquation du capacite de ce carte
     * @param dogs      tous les dogmes de ce carte
     * @param origine   l'origine de ce carte
     * @param nbCroyant le nombre de croyants qu'il peut attacher
     * @return
     */


    public Guide createClerc(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant, Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogs);


        return new Guide(nom, capacite, temp, origine, nbCroyant, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                int numPoint = ((Guide)parameters.getThisC()).getNbCroyant();
                int choix;
                if(parameters.getMyself() instanceof JoueurPhysique){
                    Object[] obj =new Object[3];
                   obj[0] = "jour";
                    obj[1] = "nuit";
                    obj[2] = "neant";

                    String name = (String) JOptionPane.showInputDialog(null,"Choisissez l'origine des points d'action que vous voulez gagner\n", "origine", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, "jour");
                    if(name == "jour")
                        choix = 0;
                    else if(name == "nuit")
                        choix = 1;
                    else
                        choix = 2;
                    /*while(choix != 0 && choix != 1 && choix != 2){
                        choix = scanner.nextInt();
                    }*/
                }else{
                    choix = (int) Math.random()*2;
                }
                    switch (choix){
                        case 0 :
                            parameters.getMyself().getPointActTot().setJour(numPoint);
                            break;
                        case 1:
                            parameters.getMyself().getPointActTot().setNuit(numPoint);
                            break;
                        default:
                            parameters.getMyself().getPointActTot().setNeant(numPoint);
                            break;
                    }


            }
        },image);
    }

    public Guide createMessie(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogs);
        return new Guide(nom, capacite, temp, origine, nbCroyant, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    Object[] obj =new Object[3];
                    obj[0] = "jour";
                    obj[1] = "nuit";
                    obj[2] = "neant";
                    String name = (String) JOptionPane.showInputDialog(null,"Choisissez une face de dé de cosgomonie que vous voulez\n", "origine", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, "jour");

                    int choix = 0;
                    if(name == "jour")
                        choix = 0;
                    else if(name == "nuit")
                        choix = 1;
                    else
                        choix = 2;

                    Part.getPart().JoueurAjouterPoint(choix);
                    }else{
                        int choix = 0;
                        if(choix == 0){
                        choix = (int) Math.random()*6;
                        }
                    Part.getPart().JoueurAjouterPoint(choix);
                }
                int position = 0;
                for(int i =0; i< Part.getPart().getListeJouCourant().size();i ++ ){
                    if(Part.getPart().getListeJouCourant().get(i) == parameters.getMyself()){
                        position = i;
                    }
                }
                Part.getPart().setStartIndex(position);
                }
        },image);
    }

//Croyant

    /**
     * @param nom       le nom de la carte croyant Ermite
     * @param capacite  l'explication du capacite de ce carte
     * @param nbcroyant le nombre de croyants qu'il contient
     * @param dogmes    tous les dogmes de ce carte
     * @param origine   l'origine de ce carte
     * @return
     */
    public Croyant createMoins(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);


            }


        },image);
    }

    public Croyant createTravailleurs1(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    List<Joueur>listtemp = new ArrayList<Joueur>();
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){
                        Joueur joueurtemp = (Joueur)i.next();
                        for(Iterator j = joueurtemp.getDivinite().getDogmes().iterator();j.hasNext();){
                            int dogmestemp = (int)j.next();
                            if(dogmestemp == Constants.DOGMES_NATURE ||dogmestemp == Constants.DOGMES_MYSTIQUES){
                                listtemp.add(joueurtemp);
                                break;
                            }
                        }
                    }
                    Object[] obj = new Object[listtemp.size()] ;
                    int nombre = 0;
                    for(Iterator i = listtemp.iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                }

            }
        },image);
    }

    public Croyant createTravailleurs2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    List<Joueur>listtemp = new ArrayList<Joueur>();
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){
                        Joueur joueurtemp = (Joueur)i.next();
                        for(Iterator j = joueurtemp.getDivinite().getDogmes().iterator();j.hasNext();){
                            int dogmestemp = (int)j.next();
                            if(dogmestemp == Constants.DOGMES_CHAOS ||dogmestemp == Constants.DOGMES_MYSTIQUES){
                                listtemp.add(joueurtemp);
                                break;
                            }
                        }
                    }
                    Object[] obj = new Object[listtemp.size()];
                    int nombre = 0;
                    for(Iterator i = listtemp.iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                }

            }
        },image);
    }

    public Croyant createTravailleurs3(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
              /*  parameters.getMyself().setPointActTot(parameters.getMyself().getPointActTot());*/
              boolean avoirdeuxcarte = false;
                Joueur joueurtemp = null;
                while(avoirdeuxcarte = false) {
                    Object[] obj =new Object[parameters.getListotherjoueur().size()];
                    int nombre = 0;
                    for (Iterator i = parameters.getListotherjoueur().iterator(); i.hasNext(); ) {

                        Joueur key = (Joueur) i.next();
                        obj[nombre] = key.getNom();
                        nombre++;
                    }
                    String nomjoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (Iterator i = parameters.getListotherjoueur().iterator(); i.hasNext(); ) {
                        joueurtemp = (Joueur) i.next();
                        if (nomjoueur == joueurtemp.getNom()) {

                            break;
                        }
                    }

                    if (joueurtemp.getCarteMain().size() >=2) {

                        avoirdeuxcarte = true;
                        int num = (int) Math.random() * joueurtemp.getCarteMain().size();
                        Carte a = joueurtemp.getCarteMain().get(num);
                        parameters.getMyself().getCarteMain().add(a);

                        joueurtemp.getCarteMain().remove(a);
                        int num2 = (int) Math.random() * joueurtemp.getCarteMain().size();
                        Carte b = joueurtemp.getCarteMain().get(num2);
                        parameters.getMyself().getCarteMain().add(a);
                        joueurtemp.getCarteMain().remove(b);

                    }
                    if(avoirdeuxcarte = false)
                        parameters.getMyself().afficherfalse();
                }



            }
        },image);
    }

    /**
     * @param nom       le nom de la carte croyant Ermite
     * @param capacite  l'explication du capacite de ce carte
     * @param nbcroyant le nombre de croyants qu'il contient
     * @param dogmes    tous les dogmes de ce carte
     * @param origine   l'origine de ce carte
     * @return
     */
    public Croyant creatermiteorIntegristes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
                    }
              /*  Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                        List<Carte> croyanttemp = new ArrayList<Carte>();
                        List<Carte> cartemain = joueurtemp.getCarteMain();
                        Object[] obj1 = new Object[cartemain.size()];
                        int nombre = 0;
                        for (Iterator i = cartemain.iterator(); i.hasNext(); ) {

                            Carte cartetemp = (Carte) i.next();
                            obj[nombre] = cartetemp.getCapacite();
                            nombre++;
                        }
                        String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                        for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                            Carte cartetemp = (Carte) i.next();
                            if (nomcarte == cartetemp.getCapacite()) {
                /*for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                    Carte key = (Carte) i.next();
                    if (key instanceof Croyant)
                        croyanttemp.add(key);
                }*/

                                cartetemp.sacrifier(parameters);
                                break;
                            }
                        }
                    }
                }

        },image);
    }
/*
    public Croyant Integristes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                List<Carte> guidetemp = new ArrayList<Carte>();
                List<Carte> cartemain = joueurtemp.getCarteMain();
                for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                    Carte key = (Carte) i.next();
                    if (key instanceof Guide)
                        guidetemp.add(key);
                }

                guidetemp.get(joueurtemp.jouer(guidetemp)).sacrifier(parameters);

            }
        },image);
    }
*/
    public Croyant createGuerriersSaints(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                List<Carte> listguidetemp = new ArrayList<Carte>();
                /*for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                    Carte a = (Guide) i.next();
                }*/
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    Object[] obj = new Object[parameters.getMyself().getCarteGuide().size()];
                    boolean avoircarteguide = false;
                    int nombre = 0;
                    if (parameters.getMyself().getCarteGuide() != null) {
                        for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {

                            Carte cartetemp = (Carte) i.next();
                            obj[nombre] = cartetemp.getCapacite();
                            nombre++;
                        }
                        String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                            Guide cartetemp = (Guide) i.next();
                            if (nomcarte == cartetemp.getCapacite()) {
                /* cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));*/

                                parameters.getMyself().getCarteMain().add(cartetemp);
                                for (Iterator j = cartetemp.getCroyantAttache().iterator(); j.hasNext(); ) {
                                    Croyant croyant = (Croyant) j.next();
                                    VueCarte vueCarte = new VueCarte(croyant);
                                    parameters.getPart().croyantCommun.add(croyant);
                               //     MaVueTotale.getmaVueTotale().getCroyantCommunPanel().add(vueCarte);
                                }
                                parameters.getMyself().getCarteGuide().remove(cartetemp);//去掉Guide
                                break;
                            }
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas la carte Croyant ! Appuyez 'finir' pour finir.");
                }
            }
        },image);

    }
    /*
 public Croyant createDiplomates(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(dogmes);
            return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
                @Override
                public void sacrifier(Parameters parameters) {
                    Object[] obj =new Object[parameters.getListotherjoueur().size()];
                    Joueur joueurtemp = null;
                    if (parameters.getMyself() instanceof JoueurPhysique) {
                        boolean avoircrtguide = false;
                        while(avoircrtguide = false){
                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                   Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
             /*       List<Carte> guidetemp = new ArrayList<Carte>();
                    List<Carte> cartemain = joueurtemp.getCarteMain();
                for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                    Carte key = (Carte) i.next();
                    if (key instanceof Guide)
                        guidetemp.add(key);
                }
                    Object[] obj1 =new Object[parameters.getMyself().getCarteGuide().size()];
                    int nombre = 0;
                    for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {

                        Guide cartetemp = (Guide) i.next();
                        obj[nombre] = cartetemp.getCapacite();
                        nombre++;
                    }
*/
            /*        String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Guide voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                    for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                        Guide cartetemp = (Guide) i.next();
                        if (nomcarte == cartetemp.getCapacite()) {
                            cartetemp.sacrifier(parameters);
                        }

               guidetemp.get(joueurtemp.jouer(guidetemp)).sacrifier(parameters);
                    }
                }
            }
        },image);
    }*/



    public Croyant createDemons(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getNuit() + 1);


            }


        },image);
    }

    public Croyant createAlchimistes2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(dogmes);
        return new Croyant(nom, capacite, nbcroyant, temp, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
                    }
                /*Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                    int num = (int) Math.random() * joueurtemp.getCarteMain().size();
                    Carte a = joueurtemp.getCarteMain().get(num);
                    VueCarte vueCarte = new VueCarte(a);
                    parameters.getMyself().getCarteMain().add(a);
                 /*   MaVueTotale.getmaVueTotale().getCarteAMainPanel().add(vueCarte);*/
                    joueurtemp.getCarteMain().remove(a);
                    int num2 = (int) Math.random() * joueurtemp.getCarteMain().size();
                    Carte b = joueurtemp.getCarteMain().get(num2);
                    vueCarte = new VueCarte(b);
                    parameters.getMyself().getCarteMain().add(b);
                   /* MaVueTotale.getmaVueTotale().add(vueCarte);*/
                    joueurtemp.getCarteMain().remove(b);

                }
            }
        },image);
    }

    public Croyant createVampire(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
                    }
               /* Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                    List<Carte> croyanttemp = new ArrayList<Carte>();
                    List<Carte> cartemain = joueurtemp.getCarteMain();
                    for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                        Carte key = (Carte) i.next();
                        if (key instanceof Croyant)
                            croyanttemp.add(key);
                    }
                    Object[] obj1 = new Object[croyanttemp.size()];
                    int nombre = 0;
                    for (Iterator i = croyanttemp.iterator(); i.hasNext(); ) {

                        Carte cartetemp = (Carte) i.next();
                        obj[nombre] = cartetemp.getCapacite();
                        nombre++;
                    }
                    String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                    for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                        Carte cartetemp = (Carte) i.next();
                        if (nomcarte == cartetemp.getCapacite()) {
                            cartetemp.sacrifier(parameters);
                            break;
                        }

/*
                        croyanttemp.get(joueurtemp.jouer(croyanttemp)).sacrifier(parameters);*/
                    }
                }
            }

        },image);
    }

    public Croyant createLycanthropes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
                    }
             /*   Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                    List<Carte> listguidetemp = new ArrayList<Carte>();
                    for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                        Guide a = (Guide) i.next();
                        listguidetemp.add(a);
                    }
                    Object[] obj1 =new Object[listguidetemp.size()];
                    int nombre = 0;
                    for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {

                        Guide cartetemp = (Guide) i.next();
                        obj[nombre] = cartetemp.getCapacite();
                        nombre++;
                    }
                    String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                    for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                        Guide cartetemp = (Guide) i.next();
                        if (nomcarte == cartetemp.getCapacite()) {
                /*Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));*/
                            for (Iterator j = cartetemp.getCroyantAttache().iterator(); j.hasNext(); ) {
                                Croyant croyant = (Croyant) j.next();
                                parameters.getPart().croyantCommun.add(croyant);
                                cartetemp.getCroyantAttache().remove(croyant);// 还没有在图标上改变
                            }

                            parameters.getPart().getCarteDeffause().add(cartetemp);
                            joueurtemp.getCarteGuide().remove(cartetemp);
                        }
                    }
                }

            }
        },image);
    }

    public Croyant createIllusionnistes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                boolean avoircroyant = false;
                List<Carte> croyanttemp = new ArrayList<Carte>();

                while (avoircroyant = false) {
                    Object[] obj = new Object[parameters.getListotherjoueur().size()] ;
                    Joueur joueurtemp = null;
                    if (parameters.getMyself() instanceof JoueurPhysique) {
                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                 /*   joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/

                        List<Carte> cartemain = joueurtemp.getCarteMain();
                        for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                            Carte key = (Carte) i.next();
                            if (key instanceof Croyant)
                                croyanttemp.add(key);
                        }
                        if (croyanttemp != null) {
                            avoircroyant = true;
                        }

                    }
                    Object[] obj1 =new Object[croyanttemp.size()];
                    int nombre = 0;
                    for (Iterator i = croyanttemp.iterator(); i.hasNext(); ) {

                        Croyant cartetemp = (Croyant) i.next();
                        obj[nombre] = cartetemp.getCapacite();
                        nombre++;
                    }
                    String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                    for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                        Guide cartetemp = (Guide) i.next();
                        if (nomcarte == cartetemp.getCapacite()) {
                            cartetemp.sacrifier(parameters);
                            break;
                        }
                    }
                    if(avoircroyant = false)
                        parameters.getMyself().afficherfalse();
                /*croyanttemp.get(joueurtemp.jouer(croyanttemp)).sacrifier(parameters);*/
                }

            }
        },image);
    }

    public Croyant createEsprites(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getNeant() + 1);


            }


        },image);
    }

    public Croyant createAlienes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
             if(parameters.getMyself() instanceof JoueurPhysique){
                 List<Joueur>listtemp = new ArrayList<Joueur>();
                 for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){
                     Joueur joueurtemp = (Joueur)i.next();
                     for(Iterator j = joueurtemp.getDivinite().getDogmes().iterator();j.hasNext();){
                         int dogmestemp = (int)j.next();
                         if(dogmestemp == Constants.DOGMES_MYSTIQUES ||dogmestemp == Constants.DOGMES_NATURE){
                             listtemp.add(joueurtemp);
                             break;
                         }
                     }
                 }
                 Object[] obj = new Object[listtemp.size()];
                 int nombre = 0;
                 for(Iterator i = listtemp.iterator();i.hasNext();){

                     Joueur joueurtemp = (Joueur)i.next();
                     obj[nombre] = joueurtemp.getNom();
                     nombre++;
                 }
                 String nbr = (String) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);
             }

            }
        },image);
    }
    public Croyant createAlchimistes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    List<Joueur>listtemp = new ArrayList<Joueur>();
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){
                        Joueur joueurtemp = (Joueur)i.next();
                        for(Iterator j = joueurtemp.getDivinite().getDogmes().iterator();j.hasNext();){
                            int dogmestemp = (int)j.next();
                            if(dogmestemp == Constants.DOGMES_HUMAIN ||dogmestemp == Constants.DOGMES_SYMBOLES){
                                listtemp.add(joueurtemp);
                                break;
                            }
                        }
                    }
                    Object[] obj =new Object[listtemp.size()];
                    int nombre = 0;
                    for(Iterator i = listtemp.iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);
                }

            }
        },image);
    }

    public Croyant createAlienes3(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    List<Joueur>listtemp = new ArrayList<Joueur>();
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){
                        Joueur joueurtemp = (Joueur)i.next();
                        for(Iterator j = joueurtemp.getDivinite().getDogmes().iterator();j.hasNext();){
                            int dogmestemp = (int)j.next();
                            if(dogmestemp == Constants.DOGMES_MYSTIQUES ||dogmestemp == Constants.DOGMES_CHAOS){
                                listtemp.add(joueurtemp);
                                break;
                            }
                        }
                    }
                    Object[] obj =new Object[listtemp.size()];
                    int nombre = 0;
                    for(Iterator i = listtemp.iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);
                }

            }
        },image);
    }

    public Croyant createAlienes2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = new Joueur();
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                boolean avoirdeuxcarte = false;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    while (avoirdeuxcarte = false) {
                        int nbr = 0;

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }


                    /*joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                        if (joueurtemp.getCarteMain().size() >= 2) {
                            avoirdeuxcarte = true;


                            int num = (int) Math.random() * joueurtemp.getCarteMain().size();
                            Carte a = joueurtemp.getCarteMain().get(num);
                            parameters.getMyself().getCarteMain().add(a);
                            joueurtemp.getCarteMain().remove(a);
                            int num2 = (int) Math.random() * joueurtemp.getCarteMain().size();
                            Carte b = joueurtemp.getCarteMain().get(num2);
                            parameters.getMyself().getCarteMain().add(b);
                            joueurtemp.getCarteMain().remove(b);
                        }
                        if(avoirdeuxcarte = false)
                            parameters.getMyself().afficherfalse();
                    }
                }
            }
        },image);
    }

    public Croyant createRevenant(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

                Part.getPart().JoueurAjouterPoint( De.getDe().lancer());

            }
        },image);
    }

    public Croyant createRevolutionnaires(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if (parameters.getMyself() instanceof JoueurPhysique) {
              /*      int nbr = parameters.getMyself().jouer(parameters.getListotherjoueur().size());
                    List<Joueur> listjoueurtemp = parameters.getMyself().jouer(parameters.getListotherjoueur().size(), parameters.getListotherjoueur());
                    for (Iterator i = listjoueurtemp.iterator(); i.hasNext(); ) {
                        Joueur joueurtemp = (Joueur) i.next();
                       */
                    Object[]obj1 = new Object[parameters.getListotherjoueur().size()];
                    int nombre = 0;
                    for (Iterator k = parameters.getListotherjoueur().iterator(); k.hasNext(); ) {

                        Joueur joueurtemp = (Joueur) k.next();
                        obj1[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    String nomjoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj1[0]);

                    for (Iterator j = parameters.getListotherjoueur().iterator(); j.hasNext(); ) {
                        Joueur joueur = (Joueur) j.next();
                        if (nomjoueur == joueur.getNom()) {
                            int cartenbr = parameters.getMyself().jouer(joueur.getCarteMain());
                            Joueur joueurtemp1 = new Joueur();
                            List<Joueur> listotherjoueur = parameters.getListotherjoueur();
                            joueurtemp1 = parameters.getMyself();
                        /*parameters.getListotherjoueur().add(parameters.getMyself());*/
                            parameters.setMyself(joueur);
                       /* parameters.getListotherjoueur().remove(joueurtemp);*/
                            joueur.getCarteMain().get(cartenbr).sacrifier(parameters);
                            //zhe li de parameters xu yao xiu gai ma?
                            parameters.setMyself(joueurtemp1);
                            parameters.setListotherjoueur(listotherjoueur);
                        }
                    }
                }
            }
        },image);
    }

    public Croyant cretaeNihillistes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine,Image image) {

        return new Croyant(nom, capacite, nbcroyant, dogmes, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
              /*  Object[] obj = {};
                int nombre = 0;
                for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){

                    Joueur joueurtemp = (Joueur)i.next();
                    obj[nombre] = nombre+1;
                    nombre++;
                }
                Int nbr = (Int) JOptionPane.showInputDialog(null,"quel nombre voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                for(Iterator i = listcartetemp.iterator();i.hasNext();){
                    Carte cartetemp = (Carte)i.next();
                    if(nomcarte == cartetemp.getCapacite())
                        cartetemp.sacrifier(parameters);
                    break;
                }
                int nbr = parameters.getMyself().jouer(.size());
                List<Joueur> listjoueurtemp = parameters.getMyself().jouer(nbr, parameters.getListotherjoueur());
                for (Iterator i = listjoueurtemp.iterator(); i.hasNext(); ) {
                    Joueur joueurtemp = (Joueur) i.next();
                    int cartenbr = joueurtemp.jouer(joueurtemp.getCarteMain());
                    parameters.getListotherjoueur().add(parameters.getMyself());
                    parameters.setMyself(joueurtemp);
                    parameters.getListotherjoueur().remove(joueurtemp);
                    joueurtemp.getCarteMain().get(cartenbr).sacrifier(parameters);//zhe li de parameters xu yao xiu gai ma?

                }*/
            }
        },image);
    }

    public DeusEx cretaeColereDivine1(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if(parameters.getMyself() instanceof JoueurPhysique) {
                    boolean avoiracrteguide = false;
                    while (avoiracrteguide = false) {
                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }

                        if (joueurtemp.getCarteGuide() != null) {
                            List<Carte> listguidetemp = new ArrayList<Carte>();
                            for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                                Carte a = (Guide) i.next();
                                if (a.getOrigine() == Constants.ORIGINE_NEANT || a.getOrigine() == Constants.ORIGINE_NUIT) {
                                    listguidetemp.add(a);
                                }
                            }
                            if (listguidetemp != null) {
                                avoiracrteguide = true;
                                Object[] obj1 = new Object[listguidetemp.size()];
                                int nombre = 0;
                                for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {

                                    Carte cartetemp = (Carte) i.next();
                                    obj[nombre] = cartetemp.getCapacite();
                                    nombre++;
                                }
                                String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                                for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {
                                    Guide cartetemp = (Guide) i.next();
                                    if (nomcarte == cartetemp.getCapacite()) {
                                        for (Iterator j = cartetemp.getCroyantAttache().iterator(); j.hasNext(); ) {
                                            Croyant croyant = (Croyant) j.next();
                                            parameters.getPart().croyantCommun.add(croyant);
                                            VueCarte vueCroyant = new VueCarte(croyant);
                                          //  MaVueTotale.getmaVueTotale().getCroyantCommunPanel().add(vueCroyant);
                                            cartetemp.getCroyantAttache().remove(croyant);
                                            MaVueTotale.getmaVueTotale().getCroyantRecuPanel().add(vueCroyant);
                                            parameters.getPart().getCarteDeffause().add(cartetemp);

                                            joueurtemp.getCarteGuide().remove(cartetemp);//


                                        }
                                    }
                                    break;
                                }
                        /*Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));*/
                            }
                        }


                    if (avoiracrteguide = false)
                        parameters.getMyself().afficherfalse();
                }   }
            }
        },image);
    }

    public DeusEx cretaeColereDivine2(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if(parameters.getMyself() instanceof JoueurPhysique) {
                    boolean avoiracrteguide = false;
                    while (avoiracrteguide = false) {
                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }

                        if (joueurtemp.getCarteGuide() != null) {
                            List<Carte> listguidetemp = new ArrayList<Carte>();
                            for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                                Carte a = (Guide) i.next();
                                if (a.getOrigine() == Constants.ORIGINE_JOUR || a.getOrigine() == Constants.ORIGINE_NEANT) {
                                    listguidetemp.add(a);
                                }
                            }
                            if (listguidetemp != null) {
                                avoiracrteguide = true;
                                Object[] obj1 = new Object[listguidetemp.size()];
                                int nombre = 0;
                                for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {

                                    Carte cartetemp = (Carte) i.next();
                                    obj[nombre] = cartetemp.getCapacite();
                                    nombre++;
                                }
                                String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);

                                for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {
                                    Guide cartetemp = (Guide) i.next();
                                    if (nomcarte == cartetemp.getCapacite()) {
                                        for (Iterator j = cartetemp.getCroyantAttache().iterator(); j.hasNext(); ) {
                                            Croyant croyant = (Croyant) j.next();
                                            parameters.getPart().croyantCommun.add(croyant);
                                            VueCarte vueCroyant = new VueCarte(croyant);
                                     //       MaVueTotale.getmaVueTotale().getCroyantCommunPanel().add(vueCroyant);
                                            cartetemp.getCroyantAttache().remove(croyant);
                                            MaVueTotale.getmaVueTotale().getCroyantRecuPanel().add(vueCroyant);
                                            parameters.getPart().getCarteDeffause().add(cartetemp);

                                            joueurtemp.getCarteGuide().remove(cartetemp);//


                                        }
                                    }
                                    break;
                                }
                        /*Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));*/
                            }
                        }


                    if (avoiracrteguide = false)
                        parameters.getMyself().afficherfalse();
                }  }
            }
        },image);
    }

    public DeusEx createOrdreCeleste(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    boolean avoircrtguide = false;
                    while (avoircrtguide = false) {
                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                /*Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/

                 /*   if(joueurtemp.getCarteGuide() != null) {

                        nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                        if(joueurtemp.getCarteGuide() == null)
                            parameters.getMyself().afficherfalse();
                /*    joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/

                        if (joueurtemp.getCarteGuide() != null) {
                            avoircrtguide = true;
                            List<Guide> listguidetemp = new ArrayList<Guide>();
                            listguidetemp = joueurtemp.getCarteGuide();
                            Object[] obj1 = new Object[listguidetemp.size()];
                            int nombre = 0;
                            for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {

                                Carte cartetemp = (Carte) i.next();
                                obj[nombre] = cartetemp.getCapacite();
                                nombre++;
                            }
                            String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj1, obj[0]);
                            for (Iterator i = listguidetemp.iterator(); i.hasNext(); ) {
                                Guide cartetemp = (Guide) i.next();
                                if (nomcarte == cartetemp.getCapacite()) {

               /* Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));*/
                                    parameters.getMyself().getCarteGuide().add(cartetemp);
              /* MaVueTotale.getmaVueTotale().*/  //把卡加到guide图层里
                                    joueurtemp.getCarteGuide().remove(cartetemp);
                                }

                            }
                        }
                        if(avoircrtguide = false)
                            parameters.getMyself().afficherfalse();
                }

                }
            }
        },image);
    }


    public DeusEx cretaeFourberie(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                boolean avoirdeuxcroyant = false;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    Object[] obj = new Object[parameters.getListotherjoueur().size()];


                    List<Carte> listcroyanttemp = new ArrayList<Carte>();
                    Joueur joueurtemp = new Joueur();
                    while (avoirdeuxcroyant = false) {
                        listcroyanttemp = null;

                        int nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                  /*  joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                        for (Iterator i = joueurtemp.getCarteMain().iterator(); i.hasNext(); ) {
                            Carte cartetemp = (Carte) i.next();
                            if (cartetemp instanceof Croyant) {
                                listcroyanttemp.add(cartetemp);
                            }
                        }
                        if (listcroyanttemp.size() >= 2) {

                            avoirdeuxcroyant = true;
                            int num = (int) listcroyanttemp.size();
                            Carte a = listcroyanttemp.get(num);
                            VueCarte vueCarte = new VueCarte(a);
                            parameters.getPart().getCarteDeffause().add(a);
                            joueurtemp.getCarteMain().remove(a);
                        //    MaVueTotale.getmaVueTotale().getCarteAMainPanel().remove(vueCarte);
                            listcroyanttemp.remove(a);
                            int num2 = (int) listcroyanttemp.size();
                            Carte b = joueurtemp.getCarteMain().get(num2);
                            parameters.getPart().getCarteDeffause().add(b);
                            vueCarte = new VueCarte(b);
                            joueurtemp.getCarteMain().remove(b);
                         //   MaVueTotale.getmaVueTotale().getCarteAMainPanel().remove(vueCarte);
                            listcroyanttemp.remove(b);
                        }
                      /*  if(avoirdeuxcroyant == false)
                        parameters.getMyself().afficherfalse();*/
                      if(avoirdeuxcroyant = false)
                          parameters.getMyself().afficherfalse();
                    }

                }
            }
        },image);
    }

    public DeusEx createDiversion(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
                    }
              /*  Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/

              /*      while(joueurtemp.getCarteMain() == null) {
                        joueurtemp.afficherfalse();

                        nbr = 0;
                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                            obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                        }
                        nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                            if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                                joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                                break;
                            }
                        }
                 /*   joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                    }*/
                    int num = joueurtemp.getCarteMain().size();

                    Carte cartetemp = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                    VueCarte vueCarte = new VueCarte(cartetemp);
                    parameters.getMyself().getCarteMain().add(cartetemp);
             //       MaVueTotale.getmaVueTotale().getCarteAMainPanel().add(vueCarte);
                    joueurtemp.getCarteMain().remove(cartetemp);
                    num = num - 1;
                    Carte cartetemp2 = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                    parameters.getMyself().getCarteMain().add(cartetemp2);
                    vueCarte = new VueCarte(cartetemp2);
                   // MaVueTotale.getmaVueTotale().getCarteAMainPanel().add(vueCarte);
                    joueurtemp.getCarteMain().remove(cartetemp2);
                    num = num - 1;
                    Carte cartetemp3 = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                    vueCarte = new VueCarte(cartetemp3);
                  //  MaVueTotale.getmaVueTotale().getCarteAMainPanel().add(vueCarte);
                    parameters.getMyself().getCarteMain().add(cartetemp3);
                    joueurtemp.getCarteMain().remove(cartetemp3);

                }
            }
        },image);
    }

    public DeusEx createPhoenix(String nom, String capacite, int origine,Image image) {

        return new DeusEx(nom, capacite, origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                List<Carte> listcartetemp = new ArrayList<Carte>();
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int j = 0;
                    for (Iterator i = parameters.getMyself().getCarteMain().iterator(); i.hasNext(); ) {
                        Carte carte = (Carte) i.next();
                        if (carte instanceof Guide || carte instanceof Croyant) {
                            listcartetemp.add(carte);
                        }
                    }
                    if (listcartetemp != null) {
                        Object[] obj = new Object[listcartetemp.size()];
                        int nombre = 0;
                        for (Iterator i = listcartetemp.iterator(); i.hasNext(); ) {

                            Carte cartetemp = (Carte) i.next();
                            obj[nombre] = cartetemp.getCapacite();
                            nombre++;
                        }
                        String nomcarte = (String) JOptionPane.showInputDialog(null, "quel Carte voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                        for (Iterator i = listcartetemp.iterator(); i.hasNext(); ) {
                            Carte cartetemp = (Carte) i.next();
                            if (nomcarte == cartetemp.getCapacite())
                                cartetemp.sacrifier(parameters);
                            break;
                        }
                    /*Carte cartetemp = listcartetemp.get(parameters.getMyself().jouer(listcartetemp));*/
                    /*cartetemp.sacrifier(parameters);*/
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas carte croyant! Appuyez 'finir' pour finir.");
                    ///这张卡cartetemp， sacrifier之后还应该保留着，怎么处理？？
                }
            }
        },image);
    }
    /*public DeusEx createInfluenceJour(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
             List<Carte>listcartetemp = new ArrayList<Carte>();
                for(Iterator i = parameters.getMyself().getCarteMain().iterator();i.hasNext();){
                    Carte carte = (Carte)i.next();
                    if((carte.getOrigine() = Constants.ORIGINE_NUIT)||(carte.getOrigine() = Constants.ORIGINE_NEANT)) {
                        listcartetemp.add(carte);
                    }
                }

            }

        });
    }*/


    //Divinite

    public Divinite createYarstur(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                for(Iterator i = parameters.getPart().getCartePioche().iterator();i.hasNext();){
                    Carte cartetemp = (Carte)i.next();
                    if(cartetemp instanceof Croyant && cartetemp.getOrigine() == Constants.ORIGINE_JOUR){
                        parameters.getPart().getCarteDeffause().add(cartetemp);
                        parameters.getPart().getCartePioche().remove(cartetemp);
                    }
                }
            }
        },image);
    }
    public Divinite createKillinstred(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                //boolean avoirapocalypse = false;
                //  while(avoirapocalypse = false) {
                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null; boolean avoirApocalypse = false;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
               /*     Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/

                        for (Iterator it = joueurtemp.getCarteMain().iterator(); it.hasNext(); ) {
                            Carte cartetemp = (Carte) it.next();
                            if (cartetemp instanceof Apocalypse) {
                                cartetemp.sacrifier(parameters);
                                avoirApocalypse = true;
                                break;
                            }
                        }

                    }
                    if (avoirApocalypse = false)
                        JOptionPane.showMessageDialog(null, "il n'a pas la Carte Apocalypse ! appuyez 'finir' pour finir");
                }
            }
        },image);
    }

    public Divinite createPuiTara(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                for(Iterator i = parameters.getPart().getCartePioche().iterator();i.hasNext();){
                    Carte cartetemp = (Carte)i.next();
                    if(cartetemp instanceof Croyant && cartetemp.getOrigine() == Constants.ORIGINE_JOUR){
                        parameters.getPart().getCarteDeffause().add(cartetemp);
                        parameters.getPart().getCartePioche().remove(cartetemp);
                    }
                }
            }
        },image);
    }

    public Divinite createGwenghelen(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                int nbrguide = 0;
                for(Iterator i = parameters.getMyself().getCarteMain().iterator();i.hasNext();){
                    Carte cartetemp = (Carte)i.next();
                    if(cartetemp instanceof Guide){
                        nbrguide++;
                    }
                }
                for(Iterator i = parameters.getMyself().getCarteGuide().iterator();i.hasNext();){
                    Carte cartetemp = (Carte)i.next();
                    if(cartetemp instanceof Guide){
                        nbrguide++;
                    }
                }
                parameters.getMyself().getPointActTot().setNeant(parameters.getMyself().getPointActTot().getNeant() + nbrguide);
            }
        },image);
    }

    public Divinite createShingva(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                boolean avoirdogmes = false;

                lableA:
                for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext(); ){
                    Joueur joueurtemp = (Joueur) i.next();
                   // lableB:
                    for(Iterator j = joueurtemp.getCarteMain().iterator();j.hasNext();){
                       Carte cartetemp = (Carte)j.next();
                       // lableC:
                        for(Iterator f = cartetemp.getDogmes().iterator();f.hasNext(); ){
                            int dogmes = (int)f.next();
                            if(dogmes == Constants.DOGMES_NATURE || dogmes == Constants.DOGMES_SYMBOLES){
                                avoirdogmes = true;
                                parameters.getListotherjoueur().add(parameters.getMyself());
                                parameters.getListotherjoueur().remove(joueurtemp);
                                parameters.setMyself(joueurtemp);
                                cartetemp.sacrifier(parameters);
                                break lableA;

                            }
                        }

                    }
                }
            }
        },image);
    }

    public Divinite createGorpa(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

                Object[] obj = new Object[parameters.getListotherjoueur().size()];
                Joueur joueurtemp = null;
                if (parameters.getMyself() instanceof JoueurPhysique) {
                    int nbr = 0;
                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {

                        obj[nbr] = parameters.getListotherjoueur().get(nbr + 1).getNom();
                    }
                    String nomJoueur = (String) JOptionPane.showInputDialog(null, "quel Joueur voulez-vous choisir?\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                    for (nbr = 0; nbr < parameters.getListotherjoueur().size() - 1; nbr++) {
                        if (nomJoueur == parameters.getListotherjoueur().get(nbr + 1).getNom()) {
                            joueurtemp = parameters.getListotherjoueur().get(nbr + 1);
                            break;
                        }
               /* Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));*/
                        int nbrPointActionjour = joueurtemp.getPointActTot().getJour();
                        int nbrPointActionnuit = joueurtemp.getPointActTot().getNuit();
                        int nbrPointActionneant = joueurtemp.getPointActTot().getNeant();
                        parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + nbrPointActionjour);
                        parameters.getMyself().getPointActTot().setNuit(parameters.getMyself().getPointActTot().getNuit() + nbrPointActionnuit);
                        parameters.getMyself().getPointActTot().setNeant(parameters.getMyself().getPointActTot().getNeant() + nbrPointActionneant);
                        joueurtemp.getPointActTot().setJour(0);
                        joueurtemp.getPointActTot().setNuit(0);
                        joueurtemp.getPointActTot().setNeant(0);
                    }
                }
            }
        },image);
    }






    public Divinite createRomec(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                    Object[] obj = new Object[parameters.getListotherjoueur().size()];
                    int nombre = 0;
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                }

            }
        },image);
    }


    public Divinite createDrinded(String nom, String description,String nomCapacite, List<Integer> dogs, int origine,Image image) {
        return new Divinite(nom, description, nomCapacite,dogs, origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(parameters.getMyself() instanceof JoueurPhysique){
                   Object[] obj = new Object[parameters.getListotherjoueur().size()];
                    int nombre = 0;
                    for(Iterator i = parameters.getListotherjoueur().iterator();i.hasNext();){

                        Joueur joueurtemp = (Joueur)i.next();
                        obj[nombre] = joueurtemp.getNom();
                        nombre++;
                    }
                    int nbr = (int) JOptionPane.showInputDialog(null,"Pour empecher un Divinite: \n Choisir un joueur!\n", "nom", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, obj[0]);

                }

            }
        },image);
    }

    //Apocalype
    public Apocalypse creatApo(String nom, int origine) {
        return new Apocalypse(nom,origine, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                if(Part.getPart().getListeJouCourant().size() >3) {
                    List<Joueur> joueurCourant = parameters.getListotherjoueur();
                    Joueur min = joueurCourant.get(0);
                    boolean valider = true;
                    for (Joueur j : joueurCourant) {
                        if (min.getNbrCro() > j.getNbrCro()) {
                            min = j;
                        }
                    }
                    for(Joueur j : joueurCourant){
                        if (min.getNbrCro() == j.getNbrCro()){
                            valider = false;
                        }
                    }
                    if(valider) {
                        JOptionPane.showMessageDialog(null,"Joueur " + min + "lose" );
                        Part.getPart().getListeJouCourant().remove(min);



                    }else{
                        Part.getPart().start();
                    }
                }else{
                    List<Joueur> joueurCourant = parameters.getListotherjoueur();
                    Joueur max = joueurCourant.get(0);
                    boolean valider = true;
                    for (Joueur j : joueurCourant) {
                        if (max.getNbrCro() > j.getNbrCro()) {
                            max = j;
                        }

                    }
                    for(Joueur j : joueurCourant){
                        if (max.getNbrCro() == j.getNbrCro()){
                            valider = false;
                        }
                    }
                   if(valider){
                       JOptionPane.showMessageDialog(null,"Joueur " + max + "win" );


                       Part.getPart().whowin();
                   } else{
                       Part.getPart().start();
                   }
                }
            }
        });
    }


//Deus-ex
}
