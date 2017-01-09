package classes.carte;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 03/12/2016.
 */
public class Guide extends Carte{
    private Sacrifier sac;
    private int nbCroyant;
    private int nbCryAttahce;
    private List<Croyant> croyantAttache = new ArrayList();

    public int getNbCroyant() {
        return nbCroyant;
    }

    public void setNbCroyant(int nbCroyant) {
        this.nbCroyant = nbCroyant;
    }

    public int getNbCryAttahce() {
        return nbCryAttahce;
    }

    public void setNbCryAttahce(int nbCryAttahce) {
        this.nbCryAttahce = nbCryAttahce;
    }

    public List<Croyant> getCroyantAttache() {
        return croyantAttache;
    }

    public void setCroyantAttache(List<Croyant> croyantAttache) {
        this.croyantAttache = croyantAttache;
    }

    public Guide(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant, Sacrifier sac, Image image) {
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setDogmes(dogs);
        this.setOrigine(origine);
        this.sac = sac;
        this.nbCroyant = nbCroyant;
        this.nbCryAttahce = 0;
        this.croyantAttache = null;
        this.setImage(image);

    }

    public void poser(){

    }




    @Override
    public Sacrifier sacrifier(Parameters parameters) {
        parameters.setThisC(this);
        if (sac != null) {
            sac.sacrifier(parameters);
        }
        return null;


    }

    @Override
    public String toString() {
        return "Guide{" +
                "nom : " + getNomCarte() +
                ", dogmes : " + getDogmes() +
                ", origine : " + getOrigine() +
                ", capacite : " + getCapacite() +
                ", nbCroyant : " + nbCroyant +
                ", nbCryAttahce : " + nbCryAttahce +
                ", croyantAttache : " + croyantAttache +  '}' +"\n"
               ;
    }
}
