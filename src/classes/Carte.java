package classes;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangmeng on 10/11/2016.
 */
public abstract class Carte{


    private int origine;
    private List<Integer> dogmes = new ArrayList();
    ArrayList<Integer>();
    private String nomCarte;
    private String capacite;

    public int getOrigine() {
        return origine;
    }

    public void setOrigine(int origine) {
        this.origine = origine;
    }

    public List<Integer> getDogmes() {
        return dogmes;
    }

    public void setDogmes(List<Integer> dogmes) {
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

    public abstract Sacrifier sacrifier(Parameters parameters);



}


/*    public void test(){
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.contains(Constants.DOGMES_HUMAIN);

    } */