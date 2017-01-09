package classes.carte;

import java.util.List;
import java.awt.Image;

/**
 * Created by zhangmeng on 11/11/2016.
 */
public class DeusEx extends Carte{
    private Sacrifier sac;

    public DeusEx(String nom, String capacite, int origine,Sacrifier sac,Image image) {
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setOrigine(origine);
        this.sac = sac;
        this.setImage(image);
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
        return "DeusEx{" +
                "nom : " + getNomCarte() +
                ", capacite : " + getCapacite() +
                ", originie : " + getOrigine() + '}' +"\n"
                ;
    }
}
