package classes.joueur;

import classes.Constants;

/**
 * Created by Administrator on 2016/12/4.
 */
public class PointAction {
    private int jour = 0;
    private int nuit = 0;
    private int neant = 0;

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getNuit() {
        return nuit;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public int getNeant() {
        return neant;
    }

    public void setNeant(int neant) {
        this.neant = neant;
    }

    public PointAction() {
        this.jour = 0;
        this.nuit = 0;
        this.neant = 0;
    }

    public void compterPointA(int point, Constants origine) {

    }
}
