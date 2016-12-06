package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Part {

    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Croyant> croyantCommun;

    private static Part part = new Part();

    private Part(){
        this.listeJouCourant = new ArrayList();
        this.cartePioche = new ArrayList<>();
        this.carteDeffause = new ArrayList<>();
        this.croyantCommun = new ArrayList<>();
    }
    public static Part getPart(){
        return part;
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

    /**
     * Créer des cartes d'action et les ajouter dans la liste des cartes à piocher
     */
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

    public List<Carte> shuffle(){
        Carte temp1,temp2;

        for(int i =0;i<10;i++)
        {
            temp1 = this.cartePioche.get(0);
            this.cartePioche.remove(temp1);
            this.cartePioche.add(temp1);

            temp2 = this.cartePioche.get(2);
            this.cartePioche.remove(temp2);
            this.cartePioche.add(temp2);
        }
        return this.cartePioche;
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

    public void piocher(){
        for(Joueur j : listeJouCourant)
            for(int i = 0; i<1;i++){
                j.piocher(cartePioche.get(i));
                cartePioche.remove(i);
            }
    }

    public void start(){
        for(Joueur i : listeJouCourant)
        {
            i.jouer();
        }
    }

}

