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

    private Part part;

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
        return listjoueur;
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
