package classes.carte;

import java.util.List;

/**
 * Created by zhangmeng on 11/11/2016.
 */
public class DeusEx extends Carte{
    private Sacrifier sac;

    public DeusEx(String nom, String capacite, int origine,Sacrifier sac) {
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setOrigine(origine);
        this.sac = sac;
    }
        @Override
        public Sacrifier sacrifier(Parameters parameters) {

            if (sac != null) {
                sac.sacrifier(parameters);
            }
            return null;


        }

}
