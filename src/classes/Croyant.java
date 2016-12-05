package classes;

import java.util.List;

/**
 * Created by zhangmeng on 11/11/2016.
 */
public class Croyant extends Carte{
    private Sacrifier sac;
    public Croyant(String nom, String capacite, int nbcroyant, List<Integer> dogs, int origine, Sacrifier sac){
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setDogmes(dogs);
        this.setOrigine(origine);
        this.sac = sac;


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




}
