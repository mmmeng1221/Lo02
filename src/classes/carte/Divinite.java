package classes.carte;

import java.awt.image.BufferedImage;
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

    public String origine (int ori){
        String or;
        switch (ori){
            case 21:
                or = "Jour";
            break;
            case 22:
                or = "Aube";
            break;
            case 23:
                or = "Néant";
            break;
            case 24:
                or = "Crépuscule";
            break;
            default:
                or = "Nuit";
            break;
        }
        return or;

    }
    @Override
    public Sacrifier sacrifier(Parameters parameters) {
        return null;
    }

    @Override
    public String toString() {
        return "votre carte divinité : " + "Divinite{" +  "\n" +
                "nom : " + nom + "\n" +
                "origine : " + origine(getOrigine()) + "\n" +
                "nomcCpacite : '" + nomcCpacite + '\n' +
                "description : '" + description + '\n' +
                '}';
    }
}
