package classes;

import classes.carte.*;
import classes.joueur.Joueur;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Part {

    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Croyant> croyantCommun;
    private List<Divinite> carteDivi;

    private static Part part = new Part();

    private Part(){
        this.listeJouCourant = new ArrayList();
        this.cartePioche = new ArrayList<>();
        this.carteDeffause = new ArrayList<>();
        this.croyantCommun = new ArrayList<>();
        this.carteDivi = new ArrayList<>();

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

    public void JoueurAjouterPoint(int de){
        if(de == 1 || de ==2 ){
            for(Joueur joueur : Part.getPart().getListeJouCourant()){
                if(joueur.getDivinite().getOrigine() == Constants.ORIGINE_JOUR){
                    joueur.getPointActTot().setJour(joueur.getPointActTot().getJour()+2);
                }
                else if(joueur.getDivinite().getOrigine() == Constants.ORIGINE_AUBE){
                    joueur.getPointActTot().setJour(joueur.getPointActTot().getJour()+1);
                }
            }
        }
        else if(de == 3 || de ==4 ){
            for(Joueur joueur : Part.getPart().getListeJouCourant()){
                if(joueur.getDivinite().getOrigine() == Constants.ORIGINE_NUIT){
                    joueur.getPointActTot().setNuit(joueur.getPointActTot().getNuit()+2);
                }
                else if(joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE){
                    joueur.getPointActTot().setNuit(joueur.getPointActTot().getNuit()+1);
                }
            }
        }
        else{for(Joueur joueur : Part.getPart().getListeJouCourant()){
            if(joueur.getDivinite().getOrigine() == Constants.ORIGINE_AUBE||joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE){
                joueur.getPointActTot().setNeant(joueur.getPointActTot().getNeant()+1);
            }
        }

        }

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
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant alchimistes2= Factory.getFactory().createAlchimistes2("dazhangmeng", "wo zhe ge pai mei yong de, zhen de",
                1, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(alchimistes2);






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

    /**
     * Joueur obtient une carte divinité
     */
    public void piocherDivi(){
        for(Joueur j : listeJouCourant){
            j.setDivi(carteDivi.get(0));
            carteDivi.remove(0);
        }
    }

    /**
     * Ini
     */
    public void piocher(){
        for(Joueur j : listeJouCourant)
            for(int i = 0; i<1;i++){
                j.piocher(cartePioche.get(i));
                cartePioche.remove(i);
            }
    }

    public Carte piocher1Carte(){
        Carte carte = cartePioche.get(0);
        cartePioche.remove(carte);
        return carte;
    }

    public void start(){
        for(Joueur i : listeJouCourant)
        {
            i.jouer();
        }
    }

}

