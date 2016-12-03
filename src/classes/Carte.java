package classes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by zhangmeng on 10/11/2016.
 */
public abstract class Carte {


    private Constants origine;
    private Set<Integer> dogmes = new HashSet<Integer>();
    private String nomCarte;
    private String capacite;

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Set<Integer> getDogmes() {
        return dogmes;
    }

    public void setDogmes(Set<Integer> dogmes) {
        this.dogmes = dogmes;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }
/*    public void test(){
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.contains(Constants.DOGMES_HUMAIN);

    } */

}
