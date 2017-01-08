package classes;

import classes.carte.*;
import classes.joueur.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

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


    /**
     * Créer des cartes d'action et les ajouter dans la liste des cartes à piocher
     */
    public void initialiserCarte() throws IOException{

        List<Integer> dogs = new ArrayList();
        /**
         * Guide
         */
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);
        File file = new File("Image:Guide:Martyr 1.jpg");
        Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide martyr1 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_JOUR, 2,image);
        this.cartePioche.add(martyr1);


        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        file = new File("Image:Guide:Martyr 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide martyr2 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_NUIT, 2,image);
        this.cartePioche.add(martyr2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Guide:Martyr 3.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide martyr3 = Factory.getFactory().createMartyr("Martyr", "Equivalent à la pose d'une carte Apocalypse.",
                dogs, Constants.ORIGINE_NEANT, 2,image);
        this.cartePioche.add(martyr3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Guide:Clerc 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc1 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
        ,dogs,Constants.ORIGINE_JOUR,2,image);
        this.cartePioche.add(clerc1);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_NATURE);
        file = new File("Image:Guide:Clerc 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc2 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2,image);
        this.cartePioche.add(clerc2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        file = new File("Image:Guide:Clerc 3.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc3 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NEANT,2,image);
        this.cartePioche.add(clerc3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_NATURE);
        file = new File("Image:Guide:Clerc 4.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc4 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_JOUR,2,image);
        this.cartePioche.add(clerc4);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        file = new File("Image:Guide:Clerc 5.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc5 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2,image);
        this.cartePioche.add(clerc5);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Guide:Clerc 6.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Guide clerc6 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NEANT,2,image);
        this.cartePioche.add(clerc6);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Guide:Clerc 7.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc7 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_JOUR,2,image);
        this.cartePioche.add(clerc7);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        file = new File("Image:Guide:Clerc 8.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Guide clerc8 = Factory.getFactory().createClerc("Clerc", "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées. L'Origine des points d'Action est au choix du joueur."
                ,dogs,Constants.ORIGINE_NUIT,2,image);
        this.cartePioche.add(clerc8);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Moins 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant moins = Factory.getFactory().createMoins("Moins", "Donne un point d'Action d'Origine Jour",
                2, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(moins);

        /**
         * Croyant
         */
        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Travailleur 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant travailleurs1 = Factory.getFactory().createTravailleurs1("Travailleurs", "Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour.",
                2, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(travailleurs1);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Travailleur 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant travailleurs2 = Factory.getFactory().createTravailleurs2("Travailleurs", "Empêche une Divinité possédant le Dogme Chaos ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour.",
                2, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(travailleurs2);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Travailleur 3.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant travailleurs3 = Factory.getFactory().createTravailleurs3("Travailleurs", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(travailleurs3);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Ermite 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant ermite1 = Factory.getFactory().creatermiteorIntegristes("Ermite", "Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(ermite1);

        dogs = new ArrayList<>();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        file = new File("Image:Croyant:Guerriers Saints.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant GuerriersSaints = Factory.getFactory().createGuerriersSaints("GuerriersSaints", "Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table.",
                4, dogs, Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(GuerriersSaints);


        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Alchimistes 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant alchimistes2= Factory.getFactory().createAlchimistes2("Alchimistes", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(alchimistes2);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        file = new File("Image:Croyant:Vampire 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant vampire1= Factory.getFactory().createVampire("Vampire", "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(vampire1);

        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Vampire 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant vampire2= Factory.getFactory().createVampire("Vampire", "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée.",
                1, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(vampire2);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Moins 1Lycanthropes 1.jpg");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant lycanthropes= Factory.getFactory().createLycanthropes("Lycanthropes", "Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé.",
                4, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(lycanthropes);

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Illusionnistes 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Illusionnistes= Factory.getFactory().createIllusionnistes("Illusionnistes", "Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyants appartenant à une autre Divinité. La carte en question reste en jeu.",
                4, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(Illusionnistes);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        file = new File("Image:Croyant:Esprites 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Esprites= Factory.getFactory().createEsprites("Esprites", "Donne un point d'Action d'Origine Néant.",
                2, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Esprites);

        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Alienes 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Alienes= Factory.getFactory().createAlienes("Alienes", "Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",
                2, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Alienes);

        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        file = new File("Image:Croyant:Alienes 3.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Alienes3= Factory.getFactory().createAlienes3("Alienes", "Empêche une Divinité possédant le Dogme Mystique ou Chaos de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
                2, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Alienes3);


        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Alienes 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Alienes2= Factory.getFactory().createAlienes2("Alienes", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                2, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Alienes2);

        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        file = new File("Image:Croyant:Revenant 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Revenant= Factory.getFactory().createRevenant("Alienes", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",
                1, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Revenant);


        dogs.clear();
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Alchimistes 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Alchimistes= Factory.getFactory().createAlchimistes("Alchimistes", "Empêche une Divinité possédant le Dogme Humain ou Symboles de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",
                2, dogs, Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(Alchimistes);


        dogs.clear();
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_CHAOS);
        file = new File("Image:Croyant:Revolutionnaire 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Croyant Revolutionnaire= Factory.getFactory().createRevolutionnaires("Revolutionnaire", "Imposez le sacrifice d'une carte de Croyants à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.",
                2, dogs, Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Revolutionnaire);

        //DeusEx
        file = new File("Image:DeuxEx:Colere Divine 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeusEx ColereDivine1= Factory.getFactory().cretaeColereDivine1("Colere Divine", "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.", Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(ColereDivine1);

        file = new File("Image:DeuxEx:Colere Divine 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeusEx ColereDivine2= Factory.getFactory().cretaeColereDivine2("Colere Divine", "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.", Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(ColereDivine2);

        file = new File("Image:DeuxEx:Ordre Celeste 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeusEx OrdreCelestes= Factory.getFactory().createOrdreCeleste("Ordre Celestes", "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.", Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(OrdreCelestes);

        file = new File("Image:DeuxEx:Fourberie 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeusEx Fourberie= Factory.getFactory().cretaeFourberie("Fourberie", "Sacrifiez 2 cartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.", Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(Fourberie);

        file = new File("Image:DeuxEx:Diversion 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DeusEx Diversion= Factory.getFactory().createDiversion("Diversion", "Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.", Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(Diversion);

        file = new File("Image:DeuxEx:Concentration 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DeusEx Concentration= Factory.getFactory().createOrdreCeleste("Concentration", "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.", Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Concentration);

        file = new File("Image:DeuxEx:Phoenix 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeusEx Phoenix= Factory.getFactory().createPhoenix("Phoenix", "Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.", Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(Phoenix);

        /**
         * Apocalypse
         */
        file = new File("Image:Apocalypse:Apocalypse 1.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apocalypse apocalypse1 = Factory.getFactory().creatApo("Apocalyipse",Constants.ORIGINE_JOUR,image);
        this.cartePioche.add(apocalypse1);
        file = new File("Image:Apocalypse:Apocalypse 2.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apocalypse apocalypse2 = Factory.getFactory().creatApo("Apocalyipse",Constants.ORIGINE_NUIT,image);
        this.cartePioche.add(apocalypse2);

        file = new File("Image:Apocalypse:Apocalypse 3.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apocalypse apocalypse3 = Factory.getFactory().creatApo("Apocalyipse",Constants.ORIGINE_NEANT,image);
        this.cartePioche.add(apocalypse3);

        file = new File("Image:Apocalypse:Apocalypse 4.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apocalypse apocalypse4 = Factory.getFactory().creatApo("Apocalyipse",0,image);
        this.cartePioche.add(apocalypse4);

        file = new File("Image:Apocalypse:Apocalypse 5.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Apocalypse apocalypse5 = Factory.getFactory().creatApo("Apocalyipse",0,image);
        this.cartePioche.add(apocalypse5);

        /**
         * Divinite
         */

        file = new File("Image:Divinite:Divinite Killinstred.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_CHAOS);
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        Divinite Killinstred= Factory.getFactory().createKillinstred("Killinstred","Divinité machiavélique et manipulatrice,Killinstred cherche à influencer et contrôler ses ennemis." , "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.",dogs,Constants.ORIGINE_JOUR,image);
        this.carteDivi.add(Killinstred);

        file = new File("Image:Divinite:Divinite Ilenella.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Llewella= Factory.getFactory().createKillinstred("Llewella","Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis." , "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.",dogs,Constants.ORIGINE_NUIT,image);
        this.carteDivi.add(Llewella);

        file = new File("Image:Divinite:Divinite Pni-Tara.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Divinite PuiTara= Factory.getFactory().createPuiTara("Pui-Tara","Pui-Tara est la Divinité sur laquelle l'influence de la Nuit s'est faite la plus forte." , "Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour.",dogs,Constants.ORIGINE_NUIT,image);
        this.carteDivi.add(PuiTara);

        file = new File("Image:Divinite:Divinite Gnenghelen.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Divinite Gwenghelen= Factory.getFactory().createGwenghelen("Gwenghelen","Première Divinité à recevoir l'influence du Néant, Gwenghelen est celle qui en a reçu le plus de puissance." , "Récupère autant de points d'Action supplémentaires d'Origine Néant que le nombre de Guides Spirituels que la Divinité possède.",dogs,Constants.ORIGINE_AUBE,image);
        this.carteDivi.add(Gwenghelen);

        file = new File("Image:Divinite:Divinite Shingva.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_MYSTIQUES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Shingva= Factory.getFactory().createShingva("Shingva","Perverse et retorse, Shingva est une Divinité que toutes les autres détestent." , "Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",dogs,Constants.ORIGINE_AUBE,image);
        this.carteDivi.add(Shingva);

        file = new File("Image:Divinite:Divinite Gorpa.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_SYMBOLES);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Gorpa= Factory.getFactory().createGorpa("Gorpa","Divinité joueuse et espiègle, Gorpa aime gêner ses consoeurs dans leur recherche de puissance." , "Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.",dogs,Constants.ORIGINE_CREPUSCULE,image);
        this.carteDivi.add(Gorpa);

        file = new File("Image:Divinite:Divinite Rontec.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_CHAOS);
        Divinite Romec = Factory.getFactory().createRomec("Romecs","Romtec est une Divinité individualiste, pour qui chaque être vivant doit garder son libre arbitre." , "Peut empêcher un jour de créer un Guide Spirituel.La carte est défaussée.",dogs,Constants.ORIGINE_CREPUSCULE,image);
        this.carteDivi.add(Romec);


        file = new File("Image:Divinite:Divinite Drinded.jpg");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dogs.clear();
        dogs.add(Constants.DOGMES_HUMAIN);
        dogs.add(Constants.DOGMES_NATURE);
        dogs.add(Constants.DOGMES_SYMBOLES);
        Divinite Drinded = Factory.getFactory().createDrinded("Drinded","Défenseur des hommes, quelles que soient leurs croyances, Drinded protège les chefs religieux" , "Peut empêcher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.",dogs,Constants.ORIGINE_JOUR,image);
        this.carteDivi.add(Drinded);


System.out.println(cartePioche.size());
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
    public void initialiserJoueur(int nbrJ) {
        JoueurPhysique jp = new JoueurPhysique();
        this.listeJouCourant.add(jp);
        /*
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
        if(mode == 0){
        for (int i = 0; i < num; i++) {
                JoueurVirtuel jv = new JoueurVirtuel(new EasyStrategy());
            this.listeJouCourant.add(jv);}
        }else{for (int i = 0; i < num; i++) {
            JoueurVirtuel jv = new JoueurVirtuel(new HardStrategy());
            this.listeJouCourant.add(jv);}
        }*/

    }

    public void facile(int nbr){
        for (int i = 0; i < nbr; i++) {
            JoueurVirtuel jv = new JoueurVirtuel(new EasyStrategy());
            this.listeJouCourant.add(jv);}
    }

    public void dur(int nbr){
        for (int i = 0; i < nbr; i++) {
            JoueurVirtuel jv = new JoueurVirtuel(new HardStrategy());
            this.listeJouCourant.add(jv);}
    }
    /**
     * Joueur obtient une carte divinité
     */
    public void piocherDivi() {
        for (Joueur j : listeJouCourant) {
            j.setDivi(this.carteDivi.get(0));
            this.carteDivi.remove(0);
        }
    }

    /**
     * Joueurs Piochent des cartes
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
        boolean open;
        while (!isEnd) {
            open = true;
            JoueurAjouterPoint(De.getDe().lancer());
            Joueur joueur = listeJouCourant.get(startIndex);
            joueur.jouer();
            startotherJour();
            startIndex = startIndex < listeJouCourant.size() -1 ? startIndex + 1 : 0;
            open = false;
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
                System.out.println("Dé de Cosmogonie : JOUR");
                break;
            case 3:
            case 4:
                faceNuit();
                System.out.println("Dé de Cosmogonie : NUIT");
                break;
            case 5:
            case 6:
                faceNeant();
                System.out.println("Dé de Cosmogonie : NEANT");
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
