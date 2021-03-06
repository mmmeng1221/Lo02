package classes.carte;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by zhangmeng on 11/11/2016.
 */
public class Croyant extends Carte{
    private Sacrifier sac;
    public Croyant(String nom, String capacite, int nbcroyant, List<Integer> dogs, int origine, Sacrifier sac, Image image){
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setDogmes(dogs);
        this.setOrigine(origine);
        this.sac = sac;
        this.setImage(image);


    }

    public void poser(){

    }

    @Override
    public Sacrifier sacrifier(Parameters parameters) {

        if (sac != null) {
            sac.sacrifier(parameters);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Croyant{" +
                "nom : " + getNomCarte() +
                ", dogmes : " + getDogmes() +
                ", origine : " + getOrigine() +
                ", capacite : " + getCapacite() +
                '}' +"\n"
                ;
    }
}
