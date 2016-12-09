package classes.joueur;

import classes.*;
import classes.carte.*;

import java.util.*;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurPhysique extends Joueur {

    private void JoueurPhysique() {
    }

    /**
     * joueur physique choisit une carte pour jouer
     */
    @Override
    public void jouer() {
        //Dans sa phase de jouer, joueur peut choisir déffausser des cartes, ou bien compléter sa main à 7 cartes, ou bien
        // sacrifier une carte, ou bien poser une carte croyant à table et récupérer des croyants à une carte guide

        Parameters para = new Parameters();
        para.setMyself(this);

        int choix = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("vos cartes à main : " + "\n");
        this.afficherCarteAMain();
        System.out.println(this.getCarteMain().size());
        System.out.println("vos cartes guides récupérées: " + "\n");
        for (Guide card : this.getCarteGuide()) {
            System.out.println(card);
        }
        while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
            System.out.println("Vous pouvez:" + "\n" + "1.Déffausser une ou plusieurs cartes" + "\n" +
                    "2.Compléter votre main à 7 cartes" + "\n" + "3.Sacrifier une carte(croyant ou guide)" + "\n" + "4." +
                    "Utilisez les cartes d'acction que vous avez en main" + "\n" + "Saisir le numéro d'instruction");
            choix = scanner.nextInt();
        }

        //Joueur veut déffausser des cartes
        if (choix == 1) {
            this.afficherCarteAMain();
            System.out.println("Combien de cartes voulez-vous déffausser?");

            int number = scanner.nextInt();
            for (int i = 0; i < number; i++) {
                this.afficherCarteAMain();
                System.out.println("Choisissez une cartes à déffausser");
                int cardDe = scanner.nextInt();
                this.getCarteMain().remove(this.getCarteMain().get(cardDe));
            }
        } else if (choix == 2) {//Joueur veut compléter sa main à 7 cartes
            while (this.getCarteMain().size() < 7) {
                this.piocher(Part.getPart().piocher1Carte());
            }
            this.afficherCarteAMain();
        } else if (choix == 3) {//Joueur veut sacrifier une carte croyant ou une carte guide

            System.out.println("Choisissez les cartes pas encore jouées ou les cartes récupérée." + "\n" + "0-indiquant les cartes pas " +
                    "encore jouées" + "\n" + "1-indiquant les cartes récupéreés");
            System.out.println("vos cartes à main : " + "\n");
            this.afficherCarteAMain();
            System.out.println("vos cartes récupérées: " + "\n");
            for (Guide card : this.getCarteGuide()) {
                System.out.println(card);
            }

            int num = scanner.nextInt();
            //Joueur choisit de jouer des cartes récupérées, c'est-à-dire qu'il veut sacrifier une ou plusieurs
            // cartes récupérées
            if (num == 1) {
                System.out.println("Choisissez une carte guide");
                int num1 = scanner.nextInt();
                System.out.println("Voulez-vous sacrifier ce guide ou ses croyants rattachés?" + "\n" +
                        "0-indiquant ce guide" + "\n" + "1-indiquant ses croyants rattachés");
                int num2 = scanner.nextInt();
                //Joueur choisit de sacrifier ce guide
                if (num2 == 0) {
                    this.getCarteGuide().get(num1).sacrifier(para);
                    this.getCarteGuide().get(num1).getCroyantAttache().clear();
                    this.getCarteGuide().remove(this.getCarteGuide().get(num1));

                } else if (num2 == 1) { //Joueur choisit de sacrifier ses croyants rattachés
                    System.out.println("Quel croyant voulez-vous sacrifier?");
                    for (Croyant cr : this.getCarteGuide().get(num1).getCroyantAttache()) {
                        System.out.println(cr);
                    }
                    //Joueur choisit de sacrifier un croyant par son numéro dans la liste
                    int num3 = scanner.nextInt();
                    this.getCarteGuide().get(num1).getCroyantAttache().get(num3).sacrifier(para);
                    this.getCarteGuide().get(num1).getCroyantAttache().remove(this.getCarteGuide().get(num1).getCroyantAttache().get(num3));
                    if (this.getCarteGuide().get(num1).getCroyantAttache().size() < 1) {
                        this.getCarteGuide().remove(this.getCarteGuide().get(num1));
                    }
                }
            } else if (num == 0) { //Joueur choisit de jouer des cartes pas encore jouée(cartes à main), pour la sacrifier
                this.afficherCarteAMain();
                int number = scanner.nextInt();
                Carte card = getCarteMain().get(number);
                while (!(card instanceof Croyant) && !(card instanceof Guide)) {
                    System.out.println("Vous devez choisir une carte croyant ou guide");
                    int number1 = scanner.nextInt();
                    card = getCarteMain().get(number1);
                }
                card.sacrifier(para);
                this.getCarteMain().remove(card);
            }
        } else if (choix == 4) {
            //Joueur veut poser des croyants au centre de table ou récupérer
            //des croyants, ou utiliser une carte Deux-ex ou Apocalypse
            this.afficherCarteAMain();
            boolean poser = true;
            while (poser) {
                System.out.println("Vous pouvez :" + "\n" + "0.Poser des croyants au centre de la table"
                        + "\n" + "1.Récupérer des croyants" + "\n" + "2.Utilisez une carte Deux-Ex ou Apocalypse");
                int answer = scanner.nextInt();
                while (answer != 0 && answer != 1 && answer != 2) {
                    System.out.println("Vous devez entrer 0 ou 1 ou 2");
                    answer = scanner.nextInt();
                }
                if (answer == 0) {
                    //Joueur veut poser des croyants
                    poser(scanner);
                } else if (answer == 1) {
                    //Joueur veut récupérer des croyants
                    recuperer(scanner);
                } else {
                    // Joueur veut utiliser une carte Deus-Ex ou une carte Apocalypse
                    utiliserCA(scanner,para);
                }
                //Demander au joueur s'il a fini sa phase de jouer
                System.out.println("Avez-vous fini de poser des croyants et de récupérer des croyants?"
                        + "\n" + "0-indiqant fini" + "\n" + "1-indiquant continuer à faire cette étape");
                int answer1 = scanner.nextInt();
                if (answer1 == 0) {
                    poser = false;
                    scanner.close();
                }
            }
        }
    }


    public void utiliserCA(Scanner scanner,Parameters parameters) {
        List<Carte> cardCA = new ArrayList<>();
        cardCA.addAll(this.getCarteMain());
        List<Carte> cardCAValid = new ArrayList<>();
        cardCAValid.addAll(this.getCarteValid(cardCA,DeusEx.class));
        cardCAValid.addAll(this.getCarteValid(cardCA,Apocalypse.class));
        this.afficherCarte(cardCAValid);
        System.out.println("Choisissez une carte Deux-Ex ou une carte Apocalypse");
        int ca = scanner.nextInt();
        Carte caChoisi = cardCAValid.get(ca);
        int jour = this.getPointActTot().getJour();
        int neant = this.getPointActTot().getNeant();
        int nuit = this.getPointActTot().getNuit();
        utiliserPoint(scanner,caChoisi,jour,nuit,neant);
        /*switch(caChoisi.getOrigine()){
            case Constants.ORIGINE_JOUR:
                this.getPointActTot().setJour(jour - 1);
                break;
            case Constants.ORIGINE_NUIT:
                this.getPointActTot().setNuit(nuit - 1);
                break;
            case Constants.ORIGINE_NEANT:
                if(neant > 0 && jour < 2 && nuit < 2){
                    this.getPointActTot().setNeant(neant-1);
                }else if(jour > 1 && nuit < 2 && neant < 1){
                    this.getPointActTot().setJour(jour - 2);
                }else if(nuit > 1 && jour < 2 && neant < 1){
                    this.getPointActTot().setNuit(nuit - 2);
                }else if(neant > 0 && jour > 1 && nuit < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant JOUR" + "\n" + "1-indiquant NEANT");
                    System.out.println("JOUR : " + jour + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setJour(jour - 2);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                    }
                }else if(neant > 0 && nuit > 1 && jour < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                    }
                }else if(neant < 1 && nuit > 1 && jour > 1){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else{
                        this.getPointActTot().setJour(jour-1);
                    }
                }else{
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR"+ "\n" + "2-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1 && or !=2) {
                        System.out.println("Saisir 0 ou 1 ou 2, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else if (or == 1){
                        this.getPointActTot().setJour(jour-2);
                    }else{
                        this.getPointActTot().setNeant(neant-2);
                    }
                }
                break;
            default:
                break;
        }*/
        caChoisi.sacrifier(parameters);
        this.getCarteMain().remove(caChoisi);
    }


    public void recuperer(Scanner scanner) {
        //Joueur veut récupérer des croyants
        List<Carte> cardGuide = new ArrayList<>();
        cardGuide.addAll(this.getCarteMain());
        this.afficherCarte(this.getCarteValid(cardGuide,Guide.class));
        System.out.println("Choisissez un guide pour récupérer des croyants");
        int card = scanner.nextInt();
        Carte crPoser = this.getCarteValid(cardGuide,Guide.class).get(card);//obtenir une carte guide
        /*while (!(crPoser instanceof Guide)) {
            System.out.println("Vous devez choisir une carte guide");
            card = scanner.nextInt();
            crPoser = this.getCarteMain().get(card);
        }*/
        int jour = this.getPointActTot().getJour();
        int neant = this.getPointActTot().getNeant();
        int nuit = this.getPointActTot().getNuit();

        List<Carte> cardCrCommun = new ArrayList<>();
        cardCrCommun.addAll(Part.getPart().getCroyantCommun());
        List<Carte>crValid = this.getCarteValid(cardCrCommun,Croyant.class);
        utiliserPoint(scanner,crPoser,jour,nuit,neant);
       /* switch (crPoser.getOrigine()) {
            case Constants.ORIGINE_JOUR:
                this.getPointActTot().setJour(jour - 1);
                recupereCarteJN(crPoser, scanner,crValid);
                break;
            case Constants.ORIGINE_NUIT:
                this.getPointActTot().setNuit(nuit - 1);
                recupereCarteJN(crPoser, scanner,crValid);
                break;
            default:
                if(neant > 0 && jour < 2 && nuit < 2){
                this.getPointActTot().setNeant(neant-1);
                recupereCarteJN(crPoser, scanner,crValid);
                }else if(jour > 1 && nuit < 2 && neant < 1){
                    this.getPointActTot().setJour(jour - 2);
                    recupereCarteJN(crPoser, scanner,crValid);
                }else if(nuit > 1 && jour < 2 && neant < 1){
                    this.getPointActTot().setNuit(nuit - 2);
                    recupereCarteJN(crPoser, scanner,crValid);
                }else if(neant > 0 && jour > 1 && nuit < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant JOUR" + "\n" + "1-indiquant NEANT");
                    System.out.println("JOUR : " + jour + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setJour(jour - 2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }
                }else if(neant > 0 && nuit > 1 && jour < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }
                }else if(neant < 1 && nuit > 1 && jour > 1){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }else{
                        this.getPointActTot().setJour(jour-1);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }
                }else{
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR"+ "\n" + "2-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1 && or !=2) {
                        System.out.println("Saisir 0 ou 1 ou 2, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }else if (or == 1){
                        this.getPointActTot().setJour(jour-2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }else{
                        this.getPointActTot().setNeant(neant-2);
                        recupereCarteJN(crPoser, scanner,crValid);
                    }
                }
                break;
        }*/
        //recupereCarteJN(crPoser, scanner,crValid);
    }

    public void utiliserPoint(Scanner scanner,Carte card,int jour,int nuit,int neant){
        switch(card.getOrigine()){
            case Constants.ORIGINE_JOUR:
                this.getPointActTot().setJour(jour - 1);
                break;
            case Constants.ORIGINE_NUIT:
                this.getPointActTot().setNuit(nuit - 1);
                break;
            case Constants.ORIGINE_NEANT:
                if(neant > 0 && jour < 2 && nuit < 2){
                    this.getPointActTot().setNeant(neant-1);
                }else if(jour > 1 && nuit < 2 && neant < 1){
                    this.getPointActTot().setJour(jour - 2);
                }else if(nuit > 1 && jour < 2 && neant < 1){
                    this.getPointActTot().setNuit(nuit - 2);
                }else if(neant > 0 && jour > 1 && nuit < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant JOUR" + "\n" + "1-indiquant NEANT");
                    System.out.println("JOUR : " + jour + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setJour(jour - 2);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                    }
                }else if(neant > 0 && nuit > 1 && jour < 2){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "NEAT : " + neant);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else{
                        this.getPointActTot().setNeant(neant-1);
                    }
                }else if(neant < 1 && nuit > 1 && jour > 1){
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1) {
                        System.out.println("Saisir 0 ou 1, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else{
                        this.getPointActTot().setJour(jour-1);
                    }
                }else{
                    System.out.println("Vous voulez utiliser point d'action JOUR ou NEANT?" +
                            "\n" + "0-indiquant NUIT" + "\n" + "1-indiquant JOUR"+ "\n" + "2-indiquant NEANT");
                    System.out.println("NUIT : " + nuit + "\n" + "JOUR : " + jour);
                    int or = scanner.nextInt();
                    while (or != 0 && or != 1 && or !=2) {
                        System.out.println("Saisir 0 ou 1 ou 2, s'il vous plait");
                        or = scanner.nextInt();
                    }
                    if(or == 0){
                        this.getPointActTot().setNuit(nuit - 2);
                    }else if (or == 1){
                        this.getPointActTot().setJour(jour-2);
                    }else{
                        this.getPointActTot().setNeant(neant-2);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void recupereCarteJN( Carte crPoser, Scanner scanner,List<Carte> croyantValid) {
            this.getCarteGuide().add((Guide) crPoser);
            List<Carte> crTemp = new ArrayList<>();
            List<Carte> crTot = new ArrayList<>();
            crTot.addAll(croyantValid);
            for (Carte croyant : croyantValid) {
                if (crPoser.compareDogmes(croyant.getDogmes())) {///à vérifier compareDgmes
                    crTemp.add(croyant);
                    crTot.remove(croyant);
                }
            }
            boolean continuer = true;
            while (((Guide) crPoser).getNbCryAttahce() < ((Guide) crPoser).getNbCroyant() && continuer == true) {
                for (Carte cr : crTemp) {
                    System.out.println(cr);
                }
                System.out.println("Choisissez un croyant");
                int croyantNew = scanner.nextInt();
                Carte cardCro = crTemp.get(croyantNew);
                ((Guide) crPoser).getCroyantAttache().add((Croyant) cardCro);
                Part.getPart().getCroyantCommun().remove(cardCro);
                crTot.remove(cardCro);
                crTemp.remove(cardCro);
                ((Guide) crPoser).setNbCryAttahce(((Guide) crPoser).getNbCryAttahce() + 1);
                this.setNbrCro(this.getNbrCro()+1);
                if(((Guide) crPoser).getNbCryAttahce() < ((Guide) crPoser).getNbCroyant()) {
                    System.out.println("Voulez-vous continuer à attacher des croyants?" + "\n" + "0-indiquant continuer"
                            + "\n" + "1-indiquant arrêter");
                    int recuperer = scanner.nextInt();
                    if (recuperer == 1) {
                        continuer = false;
                    }
                }
            }
            this.getCarteMain().remove(crPoser);
        }

/*
    private void recupereBy3Points(int jour, int nuit, int neant, Carte crPoser, Scanner scanner) {
        if (neant > 0 || jour > 1 || nuit > 1) {
            System.out.println("Vous voulez utiliser point d'action de jour ou de néant?" +
                    "\n" + "0-indiquant NEANT" + "\n" + "1-indiquant JOUR" + "\n" + "2-indiqant NUIT");
            System.out.println("JOUR : " + jour + "\n" + "NEAT : " + neant + "\n" + "NUIT : " + nuit);
            int or = scanner.nextInt();
            while (or != 0 && or != 1 && or != 2) {
                System.out.println("Saisir 0 ou 1 ou 2, s'il vous plait");
                or = scanner.nextInt();
            }

            switch (or) {
                case 0:
                    this.getPointActTot().setNeant(neant - 1);

                    this.getCarteMain().remove(crPoser);
                    break;
                case 1:
                    this.getPointActTot().setJour(jour - 2);
                    Part.getPart().getCroyantCommun().add((Croyant) crPoser);
                    this.getCarteMain().remove(crPoser);
                    break;
                case 2:
                    this.getPointActTot().setNuit(nuit - 2);
                    Part.getPart().getCroyantCommun().add((Croyant) crPoser);
                    this.getCarteMain().remove(crPoser);
                    break;
            }
        }
    }
*/
    /**
     * Afficher une liste de cartes que le joueur peut poser(son point d'action lui permet de poser)
     * @return Liste de cartes que le joueur peut poser
     */
    public <T extends Carte> List<Carte> getCarteValid(List<Carte> cardTot, Class<T> clazz){
        List<Carte> cardTemp = new ArrayList<>();
        List<Carte> cardMain = new ArrayList<>();
        cardMain.addAll(cardTot);

        System.out.println(cardMain.size());


        System.out.println(clazz.getName());
        if (clazz.equals(cardMain.get(0).getClass())){
            System.out.println("true");
        }
        if(this.getPointActTot().getJour() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_JOUR && card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
            if(this.getPointActTot().getJour() > 1){
                for(Carte card : cardMain){
                    if(card.getOrigine() == Constants.ORIGINE_NEANT&& card.getClass().equals(clazz)){
                        cardTemp.add(card);
                        cardMain.remove(card);
                    }
                }
            }
        }else if(this.getPointActTot().getNuit() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_NUIT && card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
            if(this.getPointActTot().getNuit() > 1){
                for(Carte card : cardMain){
                    if(card.getOrigine() == Constants.ORIGINE_NEANT && card.getClass().equals(clazz)){
                        cardTemp.add(card);
                        cardMain.remove(card);
                    }
                }
            }
        }else if(this.getPointActTot().getNeant() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_NEANT&& card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
        }
        return cardTemp;
    }

    public void afficherCarte(List<Carte> cardlist){
        for(Carte card : cardlist){
            System.out.println(card + "\n");
        }
    }


    /**
     * Joueur veut poser des croyants
     * @param scanner
     */
    public void poser(Scanner scanner) {
        this.afficherCarte(getCarteValid(this.getCarteMain(),Croyant.class));
        System.out.println("Choisissez un croyant à poser au centre");
        int card = scanner.nextInt();
        Carte crPoser = this.getCarteValid(this.getCarteMain(),Croyant.class).get(card);
/*        while (!(crPoser instanceof Croyant)) {
            System.out.println("Vous devez choisir une carte croyant");
            card = scanner.nextInt();
            crPoser = this.getCarteMain().get(card);
        }
*/
        int jour = this.getPointActTot().getJour();
        int neant = this.getPointActTot().getNeant();
        int nuit = this.getPointActTot().getNuit();

        switch (crPoser.getOrigine()) {
            case Constants.ORIGINE_JOUR:
                poseByPointJour(jour, crPoser, scanner);
                break;
            case Constants.ORIGINE_NUIT:
                poseByPointNuit(nuit, crPoser, scanner);
                break;
            default:
                poseBy3Point(jour, nuit, neant, crPoser, scanner);
                break;
        }
    }

    private void poseByPointJour(int jour, Carte crPoser, Scanner scanner) {
            this.getPointActTot().setJour(jour - 1);
            Part.getPart().getCroyantCommun().add((Croyant) crPoser);
            this.getCarteMain().remove(crPoser);
    }

    private void poseByPointNuit(int nuit, Carte crPoser, Scanner scanner) {
            this.getPointActTot().setNuit(nuit - 1);
            Part.getPart().getCroyantCommun().add((Croyant) crPoser);
            this.getCarteMain().remove(crPoser);
    }

    private void poseBy3Point(int jour, int nuit, int neant, Carte crPoser, Scanner scanner) {
            System.out.println("Vous voulez utiliser point d'action de jour ou de néant?" +
                    "\n" + "0-indiquant NEANT" + "\n" + "1-indiquant JOUR" + "\n" + "2-indiqant NUIT");
            System.out.println("Vos points d'action : " + "\n" +
                    "JOUR : " + jour + "\n" + "NEAT : " + neant + "\n" + "NUIT : " + nuit);
            int or = scanner.nextInt();
            while (or != 0 && or != 1 && or != 2) {
                System.out.println("Saisir 0 ou 1 ou 2, s'il vous plait");
                or = scanner.nextInt();
            }
            switch (or) {
                case 0:
                    this.getPointActTot().setNeant(neant - 1);
                    Part.getPart().getCroyantCommun().add((Croyant) crPoser);
                    this.getCarteMain().remove(crPoser);
                    break;
                case 1:
                    this.getPointActTot().setJour(jour - 2);
                    Part.getPart().getCroyantCommun().add((Croyant) crPoser);
                    this.getCarteMain().remove(crPoser);
                    break;
                case 2:
                    this.getPointActTot().setNuit(nuit - 2);
                    Part.getPart().getCroyantCommun().add((Croyant) crPoser);
                    this.getCarteMain().remove(crPoser);
                    break;
            }

    }


    public void afficherCarteAMain() {
        for (Carte card : this.getCarteMain())
            System.out.print(card);
    }

//Foncitons pour carte croyant
    public int jouer(Parameters parameters) {
        for (Iterator it = parameters.getListotherjoueur().iterator(); it.hasNext(); ) {
            Joueur key = (Joueur) it.next();
            System.out.println(key.getNom());
        }
        System.out.println("quel joueur voulez-vous choisir? Donnez le numero.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }
    @Override
    public int jouer(List<Carte> cartemain) {
        for (Iterator it = cartemain.iterator(); it.hasNext(); ) {
            Carte key = (Carte) it.next();
            System.out.println(key.getNomCarte());
        }
        System.out.println("quel Carte voulez-vous choisir? Donnez le numero.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public int jouer (int size){
        for(int i=1;i<=size;i++){
            System.out.println(i +" ");
        }
        System.out.println("quel nombre voulez-vous choisir?");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public List<Joueur> jouer(int size, List<Joueur>listjoueur){
        List<Joueur> listjoueurtemp = new ArrayList<Joueur>();
        List<Joueur>listjoueursortie = new ArrayList<Joueur>();
        listjoueurtemp = listjoueur;
        for(int j = 0;j<size;j++){
            for(Iterator i = listjoueurtemp.iterator();i.hasNext();){
                 Joueur joueurtemp =(Joueur)i.next();
                 System.out.println(joueurtemp.getNom() + "\n");
            }
            System.out.println("Quel joueur voulez-vous choisir? Donnez le numero");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            listjoueursortie.add(listjoueurtemp.get(i));
            listjoueurtemp.remove(i);
        }
        return listjoueursortie;
    }


    public void afficherfalse(){
        System.out.println("choisissez une autre joueur!");
    }
}
