package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Partie {

    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Croyant> croyantCommun;

    private static Partie partie = new Partie();

    private Partie(){}
    public static Partie getPartie(){
        return partie;
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
       List<Integer> dogs = new ArrayList();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);

        Guide martyr1 = Factory.getFactory().createMartyr("Martyr","Equivalent à la pose d'une carte Apocalypse.",
                dogs,Constants.ORIGINE_JOUR,2);
        this.cartePioche.add(martyr1);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Guide martyr2 = Factory.getFactory().createMartyr("Martyr","Equivalent à la pose d'une carte Apocalypse.",
                dogs,Constants.ORIGINE_NUIT,2);
        this.cartePioche.add(martyr2);

        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Guide martyr3 = Factory.getFactory().createMartyr("Martyr","Equivalent à la pose d'une carte Apocalypse.",
                dogs,Constants.ORIGINE_NEANT,2);
        this.cartePioche.add(martyr3);


    }

    public void initialiserJoueur(){
        System.out.println("Entrez le nombre de joueurs(2-10)");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        JoueurPhysique jp = new JoueurPhysique();
        this.listeJouCourant.add(jp);
        for(int i =1;i<num;i++){
            JoueurVirtuel jv = new JoueurVirtuel();
            this.listeJouCourant.add(jv);
        }
    }
}

