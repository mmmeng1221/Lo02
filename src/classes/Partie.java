package classes;

import java.util.List;
import java.util.Set;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Partie {
    private List<Joueur> listeJou;
    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Croyant> croyantCommun;

    private static Partie partie = new Partie();

    private Partie(){}
    public static Partie getPartie(){
        return partie;
    }

    public List<Joueur> getListeJou() {
        return listeJou;
    }

    public void setListeJou(List<Joueur> listeJou) {
        this.listeJou = listeJou;
    }

    public List<Carte> getCartePioche() {
        return cartePioche;
    }

    public void setCartePioche(List<Carte> cartePioche) {
        this.cartePioche = cartePioche;
    }

    public List<Carte> getCarteDeffause() {
        return carteDeffause;
    }

    public void setCarteDeffause(List<Carte> carteDeffause) {
        this.carteDeffause = carteDeffause;
    }

    public List<Croyant> getCroyantCommun() {
        return croyantCommun;
    }

    public void setCroyantCommun(List<Croyant> croyantCommun) {
        this.croyantCommun = croyantCommun;
    }

    public List<Joueur> getListeJouCourant() {
        return listeJouCourant;
    }

    public void setListeJouCourant(List<Joueur> listeJouCourant) {
        this.listeJouCourant = listeJouCourant;
    }


    public void initialiserCarte(){
        Set<dogmes>
        Factory.getFactory().createMartyr("martyr",null,,)
    }
}

