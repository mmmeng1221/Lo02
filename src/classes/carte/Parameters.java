package classes.carte;

import classes.De;
import classes.Part;
import classes.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Parameters {
    private List<Joueur> listjoueur = new ArrayList<Joueur>();
    private Joueur myself;
    private Joueur certain;
    private Carte thisC;

    private Part part;

    public Carte getThisC() {
        return thisC;
    }

    public void setThisC(Carte thisC) {
        this.thisC = thisC;
    }

    public De getDe() {
        return de;
    }

    public void setDe(De de) {
        this.de = de;
    }

    private De de;
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }


    public Parameters(){

    }
    public List<Joueur> getListotherjoueur() {
        return part.getListeJouCourant();
    }

    public void setListotherjoueur(List<Joueur> listotherjoueur) {
        this.listjoueur = listotherjoueur;
    }

    public Joueur getMyself() {
        return myself;
    }

    public void setMyself(Joueur myself) {
        this.myself = myself;
    }

    public Joueur getCertain() {
        return certain;
    }

    public void setCertain(Joueur certain) {
        this.certain = certain;
    }

}
