package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Parameters {
    private List<Joueur> listjoueur = new ArrayList();
    private Joueur myself;

    public Parameters(){}

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
}
