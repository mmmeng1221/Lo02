package classes.carte;

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

    public List<Croyant> getCroyantAttache() {
        return croyantAttache;
    }

    public void setCroyantAttache(List<Croyant> croyantAttache) {
        this.croyantAttache = croyantAttache;
    }

    public Guide(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant, Sacrifier sac) {
        this.setNomCarte(nom);
        this.setCapacite(capacite);
        this.setDogmes(dogs);
        this.setOrigine(origine);
        this.sac = sac;
        this.nbCroyant = nbCroyant;
        this.nbCryAttahce = 0;
        this.croyantAttache = null;

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