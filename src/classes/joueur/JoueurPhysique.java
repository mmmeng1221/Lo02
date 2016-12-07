package classes.joueur;

import classes.*;
import classes.carte.Carte;
import classes.carte.Croyant;
import classes.carte.Guide;
import classes.carte.Parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
                piocher(Part.getPart().piocher1Carte());
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
            //des croyants
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
                    utiliserCA(scanner);
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


    public void utiliserCA(Scanner scanner) {
    }


    public void recuperer(Scanner scanner) {
        //Joueur veut récupérer des croyants
        this.afficherCarteAMain();
        System.out.println("Choisissez un guide pour récupérer des croyants");
        int card = scanner.nextInt();
        Carte crPoser = this.getCarteMain().get(card);
        while (!(crPoser instanceof Guide)) {
            System.out.println("Vous devez choisir une carte guide");
            card = scanner.nextInt();
            crPoser = this.getCarteMain().get(card);
        }
        int jour = this.getPointActTot().getJour();
        int neant = this.getPointActTot().getNeant();
        int nuit = this.getPointActTot().getNuit();

        switch (crPoser.getOrigine()) {
            case Constants.ORIGINE_JOUR:
                recupereByPointJour(jour, crPoser, scanner);
                break;
            case Constants.ORIGINE_NUIT:
                recupereByPointNuit(nuit, crPoser, scanner);
                break;
            default:
                recupereBy3Points(jour, nuit, neant, crPoser, scanner);
                break;
        }
    }

    private void recupereByPointJour(int jour, Carte crPoser, Scanner scanner) {
        if (jour > 0) {
            this.getPointActTot().setJour(jour - 1);
            this.getCarteGuide().add((Guide) crPoser);
            List<Croyant> crTemp = new ArrayList<>();

            for (Croyant croyant : Part.getPart().getCroyantCommun()) {
                if (crPoser.compareDogmes(croyant.getDogmes())) {
                    crTemp.add(croyant);
                    Part.getPart().getCroyantCommun().remove(croyant);
                }
            }

            for (Croyant croyantValid : crTemp) {
                System.out.println(croyantValid);
            }

            boolean continuer = true;
            while (((Guide) crPoser).getNbCryAttahce() < ((Guide) crPoser).getNbCroyant() && continuer == true) {
                System.out.println("Choisissez un croyant");
                int croyantNew = scanner.nextInt();
                Croyant cardCro = crTemp.get(croyantNew);
                ((Guide) crPoser).getCroyantAttache().add(cardCro);
                Part.getPart().getCroyantCommun().remove(cardCro);
                ((Guide) crPoser).setNbCryAttahce(((Guide) crPoser).getNbCryAttahce() + 1);
                System.out.println("Voulez-vous continuer à attacher des croyants?" + "\n" + "0-indiquant continuer"
                        + "\n" + "1-indiquant arrêter");
                int recuperer = scanner.nextInt();
                if (recuperer == 1) {
                    continuer = false;
                }
            }
            this.getCarteMain().remove(crPoser);
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }
    }

    private void recupereByPointNuit(int nuit, Carte crPoser, Scanner scanner) {
        if (nuit > 0) {
            this.getPointActTot().setNuit(nuit - 1);
            this.getCarteMain().remove(crPoser);
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }
    }

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
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }
    }


    public void poser(Scanner scanner) {
        //Joueur veut poser des croyants
        this.afficherCarteAMain();
        System.out.println("Choisissez un croyant à poser au centre");
        int card = scanner.nextInt();
        Carte crPoser = this.getCarteMain().get(card);
        while (!(crPoser instanceof Croyant)) {
            System.out.println("Vous devez choisir une carte croyant");
            card = scanner.nextInt();
            crPoser = this.getCarteMain().get(card);
        }

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
        if (jour > 0) {
            this.getPointActTot().setJour(jour - 1);
            Part.getPart().getCroyantCommun().add((Croyant) crPoser);
            this.getCarteMain().remove(crPoser);
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }

    }

    private void poseByPointNuit(int nuit, Carte crPoser, Scanner scanner) {
        if (nuit > 0) {
            this.getPointActTot().setNuit(nuit - 1);
            Part.getPart().getCroyantCommun().add((Croyant) crPoser);
            this.getCarteMain().remove(crPoser);
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }
    }

    private void poseBy3Point(int jour, int nuit, int neant, Carte crPoser, Scanner scanner) {
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
        } else {
            System.out.println("Votre point d'action ne vous permet pas de poser ce croyant");
            ////////////// EXCEPTION???
        }
    }


    public void afficherCarteAMain() {
        for (Carte card : this.getCarteMain())
            System.out.print(card);
    }

    @Override
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

}
