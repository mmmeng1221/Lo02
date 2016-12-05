package classes;

import java.util.List;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Joueur {

    private String nom;
    private Divinite divi;
    private List<Carte> carteMain;
    private List<Guide> carteGuide;
    private PointAction pointActTot;
    private int nbrCro;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Divinite getDivinite() {
        return divi;
    }

    public void setDivinite(Divinite divinite) {
        this.divi = divinite;
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

    public Divinite getDivi() {
        return divi;
    }

    public void setDivi(Divinite divi) {
        this.divi = divi;
    }

    public PointAction getPointActTot() {
        return pointActTot;
    }

    public void setPointActTot(PointAction pointActTot) {
        this.pointActTot = pointActTot;
    }

    public int jouer(Parameters parmameters) {
        return 0;
    }
}
