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
    public static Tour tour;

    private static Part part = new Part();

    private Part(){
        this.listeJouCourant = new ArrayList();
        this.cartePioche = new ArrayList<>();
        this.carteDeffause = new ArrayList<>();
        this.croyantCommun = new ArrayList<>();
    }

    public static Tour getTour() {
        return tour;
    }

    public static void setTour(Tour tour) {
        Part.tour = tour;
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

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant moins= Factory.getFactory().createMoins("Moins", "Donne un point d'Action d'Origine Jour",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(moins);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant travailleurs3= Factory.getFactory().createTravailleurs3("Travailleurs", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(travailleurs3);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant ermite1= Factory.getFactory().creatermiteorIntegristes("Ermite", "Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(ermite1);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant GuerriersSaints= Factory.getFactory().createGuerriersSaints("GuerriersSaints", "Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table.",
                4, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(GuerriersSaints);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang= Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang2= Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(xiaozhizhang2);

        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang3= Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(xiaozhizhang3);
        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang4= Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang4);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);
        Croyant xiaozhizhang5= Factory.getFactory().zhizhang("zhangmeng", "wo zhe ge pai mei yong de, zhen de",
                1, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang5);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang6= Factory.getFactory().zhizhang("xiaozhangmeng", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(xiaozhizhang6);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang7= Factory.getFactory().zhizhang("dazhangmeng", "wo zhe ge pai mei yong de, zhen de",
                1, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(xiaozhizhang7);

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant alchimistes2= Factory.getFactory().createAlchimistes2("Alchimistes", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(alchimistes2);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant vampire1= Factory.getFactory().createVampire("Vampire", "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(vampire1);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant vampire2= Factory.getFactory().createVampire("Vampire", "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(vampire2);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant lycanthropes= Factory.getFactory().createLycanthropes("Lycanthropes", "Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé.",
                4, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(lycanthropes);

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant Illusionnistes= Factory.getFactory().createIllusionnistes("Illusionnistes", "Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyants appartenant à une autre Divinité. La carte en question reste en jeu.",
                4, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(Illusionnistes);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        Croyant Esprites= Factory.getFactory().createEsprites("Esprites", "Donne un point d'Action d'Origine Néant.",
                2, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(Esprites);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant Alienes2= Factory.getFactory().createAlienes2("Alienes", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(Alienes2);

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant Revolutionnaire= Factory.getFactory().createRevolutionnaires("Revolutionnaire", "Imposez le sacrifice d'une carte de Croyants à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.",
                2, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(Revolutionnaire);






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

