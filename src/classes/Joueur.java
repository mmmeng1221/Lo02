package classes;

import java.util.List;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Joueur {

    private String nom;
    private String Divinite;
    private List<Carte> carteMain;
    private List<Guide> carteGuide;
    private int nbrCro;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDivinite() {
        return Divinite;
    }

    public void setDivinite(String divinite) {
        Divinite = divinite;
    }

    public List<Carte> getCarteMain() {
        return carteMain;
    }

    public void setCarteMain(List<Carte> carteMain) {
        this.carteMain = carteMain;
    }

    public List<Guide> getCarteGuide() {
        return carteGuide;
    }

    public void setCarteGuide(List<Guide> carteGuide) {
        this.carteGuide = carteGuide;
    }

    public int getNbrCro() {
        return nbrCro;
    }

    public void setNbrCro(int nbrCro) {
        this.nbrCro = nbrCro;
    }


}