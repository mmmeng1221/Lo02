package classes;

import classes.carte.*;
import classes.joueur.*;

import java.util.*;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Part {

    private List<Joueur> listeJouCourant;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Croyant> croyantCommun;
    private List<Divinite> carteDivi;
    private int startIndex = 0;
    private boolean isEnd = false;

    private static Part part = new Part();

    private Part() {
        this.listeJouCourant = new ArrayList();
        this.cartePioche = new ArrayList<>();
        this.carteDeffause = new ArrayList<>();
        this.croyantCommun = new ArrayList<>();
        this.carteDivi = new ArrayList<>();

    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public static Part getPart() {
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

//    public void JoueurAjouterPoint(int de) {
//        switch (de) {
//            case 1:
//            case 2:
//        }
//        if (de == 1 || de == 2) {
//            for (Joueur joueur : Part.getPart().getListeJouCourant()) {
//                if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_JOUR) {
//                    joueur.getPointActTot().setJour(joueur.getPointActTot().getJour() + 2);
//                } else if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_AUBE) {
//                    joueur.getPointActTot().setJour(joueur.getPointActTot().getJour() + 1);
//                }
//            }
//        } else if (de == 3 || de == 4) {
//            for (Joueur joueur : Part.getPart().getListeJouCourant()) {
//                if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_NUIT) {
//                    joueur.getPointActTot().setNuit(joueur.getPointActTot().getNuit() + 2);
//                } else if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE) {
//                    joueur.getPointActTot().setNuit(joueur.getPointActTot().getNuit() + 1);
//                }
//            }
//        } else {
//            for (Joueur joueur : Part.getPart().getListeJouCourant()) {
//                if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_AUBE || joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE) {
//                    joueur.getPointActTot().setNeant(joueur.getPointActTot().getNeant() + 1);
//                }
//            }
//
//        }
//
//    }

    /**
     * Créer des cartes d'action et les ajouter dans la liste des cartes à piocher
     */
    public void initialiserCarte() {

        List<Integer> dogs = new ArrayList();
        /**
         * Guide
         */
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);
        Guide martyr1 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_JOUR, 2);
        this.cartePioche.add(martyr1);


        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Guide martyr2 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_NUIT, 2);
        this.cartePioche.add(martyr2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Guide martyr3 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_NEANT, 2);
        this.cartePioche.add(martyr3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        Guide clerc1 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
        ,dogs,Constants.ORIGINE_JOUR,2);
        this.cartePioche.add(clerc1);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_NATURE);
        Guide clerc2 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2);
        this.cartePioche.add(clerc2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        Guide clerc3 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NEANT,2);
        this.cartePioche.add(clerc3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_NATURE);
        Guide clerc4 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_JOUR,2);
        this.cartePioche.add(clerc4);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        Guide clerc5 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2);
        this.cartePioche.add(clerc5);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_CHAOS);
        Guide clerc6 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NEANT,2);
        this.cartePioche.add(clerc6);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Guide clerc7 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_JOUR,2);
        this.cartePioche.add(clerc7);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        Guide clerc8 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2);
        this.cartePioche.add(clerc8);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant moins = Factory.getFactory().createMoins("Moins", "Donne un point d'Action d'Origine Jour",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(moins);

        /**
         * Croyant
         */
        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant travailleurs3 = Factory.getFactory().createTravailleurs3("Travailleurs", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(travailleurs3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Croyant ermite1 = Factory.getFactory().creatermiteorIntegristes("Ermite", "Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(ermite1);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant GuerriersSaints = Factory.getFactory().createGuerriersSaints("GuerriersSaints", "Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table.",
                4, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(GuerriersSaints);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang = Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang2 = Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NEANT);
        this.cartePioche.add(xiaozhizhang2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang3 = Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(xiaozhizhang3);
        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang4 = Factory.getFactory().zhizhang("zhizhang", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang4);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);
        Croyant xiaozhizhang5 = Factory.getFactory().zhizhang("zhangmeng", "wo zhe ge pai mei yong de, zhen de",
                1, dogs, Constants.ORIGINE_JOUR);
        this.cartePioche.add(xiaozhizhang5);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang6 = Factory.getFactory().zhizhang("xiaozhangmeng", "wo zhe ge pai mei yong de, zhen de",
                2, dogs, Constants.ORIGINE_NUIT);
        this.cartePioche.add(xiaozhizhang6);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Croyant xiaozhizhang7 = Factory.getFactory().zhizhang("dazhangmeng", "wo zhe ge pai mei yong de, zhen de",
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

        //DeusEx
        DeusEx ColereDivine1= Factory.getFactory().cretaeColereDivine1("Colere Divine", "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.", Constants.ORIGINE_JOUR);
        this.cartePioche.add(ColereDivine1);

        DeusEx ColereDivine2= Factory.getFactory().cretaeColereDivine2("Colere Divine", "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.", Constants.ORIGINE_NUIT);
        this.cartePioche.add(ColereDivine2);

        DeusEx OrdreCelestes= Factory.getFactory().createOrdreCeleste("Ordre Celestes", "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.", Constants.ORIGINE_JOUR);
        this.cartePioche.add(OrdreCelestes);

        DeusEx Fourberie= Factory.getFactory().cretaeFourberie("Fourberie", "Sacrifiez 2 cartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.", Constants.ORIGINE_NUIT);
        this.cartePioche.add(Fourberie);

        DeusEx Diversion= Factory.getFactory().createDiversion("Diversion", "Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.", Constants.ORIGINE_NUIT);
        this.cartePioche.add(Diversion);

        DeusEx Concentration= Factory.getFactory().createOrdreCeleste("Concentration", "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.", Constants.ORIGINE_NEANT);
        this.cartePioche.add(Concentration);

        DeusEx Phoenix= Factory.getFactory().createPhoenix("Phoenix", "Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.", Constants.ORIGINE_NEANT);
        this.cartePioche.add(Phoenix);
        /**
         * Divinite
         */


        //Divinite
        //现在只有六个divinite
        dogs.clear();
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        Divinite Killinstred= Factory.getFactory().createKillinstred("Killinstred","Divinité machiavélique et manipulatrice,Killinstred cherche à influencer et contrôler ses ennemis." , "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.",dogs,Constants.ORIGINE_JOUR);
        this.carteDivi.add(Killinstred);

        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Llewella= Factory.getFactory().createKillinstred("Llewella","Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis." , "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.",dogs,Constants.ORIGINE_NUIT);
        this.carteDivi.add(Llewella);

        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Divinite PuiTara= Factory.getFactory().createPuiTara("Pui-Tara","Pui-Tara est la Divinité sur laquelle l'influence de la Nuit s'est faite la plus forte." , "Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour.",dogs,Constants.ORIGINE_NUIT);
        this.carteDivi.add(PuiTara);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Divinite Gwenghelen= Factory.getFactory().createGwenghelen("Gwenghelen","Première Divinité à recevoir l'influence du Néant, Gwenghelen est celle qui en a reçu le plus de puissance." , "Récupère autant de points d'Action supplémentaires d'Origine Néant que le nombre de Guides Spirituels que la Divinité possède.",dogs,Constants.ORIGINE_AUBE);
        this.carteDivi.add(Gwenghelen);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Shingva= Factory.getFactory().createShingva("Shingva","Perverse et retorse, Shingva est une Divinité que toutes les autres détestent." , "Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",dogs,Constants.ORIGINE_AUBE);
        this.carteDivi.add(Shingva);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Gorpa= Factory.getFactory().createGorpa("Gorpa","Divinité joueuse et espiègle, Gorpa aime gêner ses consoeurs dans leur recherche de puissance." , "Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",dogs,Constants.ORIGINE_CREPUSCULE);
        this.carteDivi.add(Gorpa);





    }

    public List<Carte> shuffle() {
        Carte temp1, temp2;
        Date date = new Date(System.currentTimeMillis());
        Random random = new Random();
        random.setSeed(date.getTime());
        for (int i = 0; i < 10; i++) {
            temp1 = this.cartePioche.get(random.nextInt(9));
            this.cartePioche.remove(temp1);
            this.cartePioche.add(temp1);

            temp2 = this.cartePioche.get(random.nextInt(5));
            this.cartePioche.remove(temp2);
            this.cartePioche.add(temp2);
        }
        return this.cartePioche;
    }

    public List<Divinite> shuffleDivi() {
        Divinite temp1, temp2;
        Date date = new Date(System.currentTimeMillis());
        Random random = new Random();
        random.setSeed(date.getTime());
        for (int i = 0; i < 10; i++) {
            temp1 = this.carteDivi.get(random.nextInt(9));
            this.carteDivi.remove(temp1);
            this.carteDivi.add(temp1);

            temp2 = this.carteDivi.get(random.nextInt(5));
            this.carteDivi.remove(temp2);
            this.carteDivi.add(temp2);
        }
        return this.carteDivi;
    }

    /**
     * Demander au utilisateur nbr de joueurs virtuels
     */
    public void initialiserJoueur() {
        System.out.println("Chioisissez le mode" + "\n" + "0-indiquant FACILE" + "\n"
        + "1-indiquant DUR");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();
        while(mode != 0 && mode !=1){
            System.out.println("Entrez 0 ou 1 s'il vous plaît.");
            mode = sc.nextInt();
        }

        System.out.println("Entrez le nombre de joueurs VIRTUELS(1-6)");

        int num = sc.nextInt();

        sc.close();

        JoueurPhysique jp = new JoueurPhysique();
        this.listeJouCourant.add(jp);

        if(mode == 0){
        for (int i = 0; i < num; i++) {

                JoueurVirtuel jv = new JoueurVirtuel(new EasyStrategy());
            this.listeJouCourant.add(jv);}
        }else{for (int i = 0; i < num; i++) {

            JoueurVirtuel jv = new JoueurVirtuel(new HardStrategy());
            this.listeJouCourant.add(jv);}

        }

    }

    /**
     * Joueur obtient une carte divinité
     */
    public void piocherDivi() {
        for (Joueur j : listeJouCourant) {
            j.setDivi(this.carteDivi.get(0));
            this.carteDivi.remove(0);
            carteDivi.remove(0);
        }
    }

    /**
     * Ini
     */
    public void piocher() {
        for (Joueur j : listeJouCourant)
            for (int i = 0; i < 7; i++) {
                j.piocher(cartePioche.get(i));
                cartePioche.remove(i);
            }
    }

    public Carte piocher1Carte() {
        Carte carte = cartePioche.get(0);
        cartePioche.remove(carte);
        return carte;
    }

    public void start() {
        isEnd = false;
        startIndex=0;
        while (!isEnd) {
            JoueurAjouterPoint(De.getDe().lancer());
            Joueur joueur = listeJouCourant.get(startIndex);
            joueur.jouer();
            startotherJour();
            startIndex = startIndex < listeJouCourant.size() -1 ? startIndex + 1 : 0;
        }

    }

    private void startotherJour() {
        int currentIndex;
        currentIndex = startIndex < listeJouCourant.size() -1 ? startIndex + 1 : 0;
        while (currentIndex != startIndex) {
            Joueur joueur = listeJouCourant.get(currentIndex);
            joueur.jouer();
            System.out.println(currentIndex + "joueur");
            currentIndex = currentIndex < listeJouCourant.size() -1 ? currentIndex + 1 : 0;
        }
    }

    public void JoueurAjouterPoint(int num) {
        switch (num) {
            case 1:
            case 2:
                faceJour();
                break;
            case 3:
            case 4:
                faceNuit();
                break;
            case 5:
            case 6:
                faceNeant();
                break;
        }
    }

    private void faceJour() {
        for (Joueur joueur : listeJouCourant) {
            if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_JOUR) {
                int point = joueur.getPointActTot().getJour();
                joueur.getPointActTot().setJour(point + 2);
            }

            if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_AUBE) {
                int point = joueur.getPointActTot().getJour();
                joueur.getPointActTot().setJour(point + 1);
            }
        }
    }

    private void faceNuit() {
        for (Joueur joueur : listeJouCourant) {
            if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_NUIT) {
                int point = joueur.getPointActTot().getNuit();
                joueur.getPointActTot().setNuit(point + 2);
            }

            if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE) {
                int point = joueur.getPointActTot().getNuit();
                joueur.getPointActTot().setNuit(point + 1);
            }
        }
    }

    private void faceNeant() {
        for (Joueur joueur : listeJouCourant) {
            if (joueur.getDivinite().getOrigine() == Constants.ORIGINE_NEANT ||
                    joueur.getDivinite().getOrigine() == Constants.ORIGINE_CREPUSCULE) {
                int point = joueur.getPointActTot().getNeant();
                joueur.getPointActTot().setNeant(point + 1);
            }
        }
    }

    public void whowin(){
        isEnd = true;
    }
}
