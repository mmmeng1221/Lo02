package classes.carte;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Created by zhangmeng on 10/11/2016.
 */
public abstract class Carte{


    private int origine;
    private List<Integer> dogmes = new ArrayList<>();
    private String nomCarte;
    private String capacite;


    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


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

    public void poser() {

    }

    /**
     * 用来比较是否有相同的dogmes
     * @param other 另一张牌的dogmes的list
     * @return 相同或不同
     */
    public boolean compareDogmes(List<Integer> other) {
        for (Integer dog : dogmes) {
            for (Integer crDog : other) {
                if (dog.equals(crDog)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "origine=" + origine +
                ", dogmes=" + dogmes +
                ", nomCarte='" + nomCarte + '\'' +
                ", capacite='" + capacite + '\'' +
                '}' + "\n";
    }
}


/*    public void test(){
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.contains(Constants.DOGMES_HUMAIN);

    } */