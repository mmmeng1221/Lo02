package classes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Parameters {
    private Set<Joueur> listjoueur = new HashSet<Joueur>();
    private Joueur myself;

    public Parameters(){

    }
    public Set<Joueur> getListotherjoueur() {
        return listjoueur;
    }

    public void setListotherjoueur(Set<Joueur> listotherjoueur) {
        this.listjoueur = listotherjoueur;
    }

    public Joueur getMyself() {
        return myself;
    }

    public void setMyself(Joueur myself) {
        this.myself = myself;
    }
}
