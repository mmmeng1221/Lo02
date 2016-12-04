package classes;

import java.util.List;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Partie {
    private List<Joueur> listeJou;
    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Carte> croyantCommun;

    private static Partie instance = new Partie();

    private Partie(){}
    public static Partie getInstance(){
        return instance;
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

    public List<Carte> getCroyantCommun() {
        return croyantCommun;
    }

    public void setCroyantCommun(List<Carte> croyantCommun) {
        this.croyantCommun = croyantCommun;
    }

    public List<Joueur> getListeJouCourant() {
        return listeJouCourant;
    }

    public void setListeJouCourant(List<Joueur> listeJouCourant) {
        this.listeJouCourant = listeJouCourant;
    }


}
