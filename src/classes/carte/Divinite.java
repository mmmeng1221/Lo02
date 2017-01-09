package classes.carte;

import java.util.List;
import java.awt.Image;

/**
 * Created by Administrator on 2016/12/4.
 */
public class Divinite extends Carte {

    public boolean capUti = true;
    public String nom;
    public String description;
    public String nomcCpacite;
    private Sacrifier sac;

    public Divinite(String nom, String description, String nomCapacite, List<Integer> dogs,int origine, Sacrifier sac,Image image){
        this.setNomCarte(nom);
        this.description = description;
        this.setCapacite(nomCapacite);
        this.setDogmes(dogs);
        this.setOrigine(origine);
        this.sac= sac;
        this.setImage(image);

    }
    public void uticap(){

    }

    @Override
    public Sacrifier sacrifier(Parameters parameters) {
        return null;
    }

    @Override
    public String toString() {
        return "votre carte divinité : " + "Divinite{" +  "\n" +
                "nom : " + nom +
                "origine : " + getOrigine() + "\n" +
                "nomcCpacite : '" + nomcCpacite + '\n' +
                "description : '" + description + '\n' +
                '}';
    }
}
