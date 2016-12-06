package classes;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurPhysique extends Joueur {

    private void JoueurPhysique() {};


    /**
     * joueur physique choisit une carte pour jouer
     */
    public void jouer() {
        //Dans sa phase de jouer, joueur peut choisir déffausser des cartes, ou bien compléter sa main à 7 cartes, ou bien
        // sacrifier une carte, ou bien poser une carte croyant à table et récupérer des croyants à une carte guide

        Parameters para = new Parameters();
        para.setMyself(this);
        int choix = 0;
        while (choix != 1 && choix != 2 && choix != 3 && choix != 4) {
            System.out.println("Vou pouvez:" + "\n" + "1.Déffausser une ou plusieurs cartes" + "\n" +
                    "2.Compléter votre main à 7 cartes" + "\n" + "3.Sacrifier une carte(croyant ou guide)" + "\n" + "4.Poser des croyants au centre de table " +
                    "ou récupérer des croyants avec une carte guide" + "\n" + "Saisir le numéro d'instruction");
            Scanner scanner = new Scanner(System.in);
            choix = scanner.nextInt();
        }
    //Joueur veut déffausser des cartes
        if (choix == 1) {
            for (Carte card : this.getCarteMain()) {
                System.out.println(card);
            }
            System.out.println("Combien de cartes voulez-vous déffausser?");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            for (int i = 0; i < number; i++) {
                for (Carte card : this.getCarteMain()) {
                    System.out.println(card);
                }
                System.out.println("Choisissez une cartes à déffausser");
                int cardDe = scanner.nextInt();
                this.getCarteMain().remove(this.getCarteMain().get(cardDe));
            }
        }

        //Joueur veut compléter sa main à 7 cartes
        else if (choix == 2) {
            while (this.getCarteMain().size() < 7) {
                this.piocher(Part.getPart().getCartePioche().get(0));
                Part.getPart().getCartePioche().remove(0);
            }
            for (Carte card : this.getCarteMain()) {
                System.out.println(card);
            }
        }

        //Joueur veut sacrifier une carte croyant ou une carte guide
        else if (choix == 3) {

            System.out.println("Choisissez les cartes pas encore jouées ou les cartes récupérée." + "\n" + "0-indiquant les cartes pas " +
                    "encore jouées" + "\n" + "1-indiquant les cartes récupéreés");

            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            //Joueur choisit de jouer des cartes récupérées, c'est-à-dire qu'il veut sacrifier une ou plusieurs
            // cartes récupérées
            if (num == 1) {
                for (Guide guide : this.getCarteGuide()) {
                    System.out.println(guide);
                }
                System.out.println("Choisissez une carte guide");
                Scanner scanner1 = new Scanner(System.in);
                int num1 = scanner1.nextInt();
                System.out.println("Voulez-vous sacrifier ce guide ou ses croyants rattachés?" + "\n" +
                        "0-indiquant ce guide" + "\n" + "1-indiquant ses croyants rattachés");
                int num2 = scanner.nextInt();
                //Joueur choisit de sacrifier ce guide
                if (num2 == 0) {


                        getCarteGuide().get(num1).sacrifier(para);
                    }

                //Joueur choisit de sacrifier ses croyants rattachés
                else if (num2 == 1) {
                    System.out.println("Quel croyant voulez-vous sacrifier?");
                    for (Croyant cr : this.getCarteGuide().get(num1).getCroyantAttache()) {
                        System.out.println(cr);
                    }
                    //Joueur choisit de sacrifier un croyant par son numéro dans la liste
                    Scanner scanner2 = new Scanner(System.in);
                    int num3 = scanner2.nextInt();
                    //Joueur choisit de sacrifier ses croyants rattachés
                    else if (num2 == 1) {
                        System.out.println("Quel croyant voulez-vous sacrifier?");
                        for (Croyant cr : getCarteGuide().get(num1).) {
                            System.out.println(cr);
                        }
                        //Joueur choisit de sacrifier un croyant par son numéro dans la liste
                        Scanner scanner2 = new Scanner(System.in);
                        int num3 = scanner2.nextInt();

                    getCarteGuide().get(num1).getCroyantAttache().get(num3).sacrifier(para);
                }
            }

            //Joueur choisit de jouer des cartes pas encore jouée(cartes à main), pour la sacrifier


                else if (num == 0) {
                    for (Carte carte : getCarteMain()) {
                        System.out.println(carte);
                    }
                    Scanner sc = new Scanner(System.in);
                    int number = sc.nextInt();
                    Carte card = getCarteMain().get(number);
                    while(!(card instanceof Croyant) && !(card instanceof Guide))
                    {
                        System.out.println("Vous devez choisir une carte croyant ou guide");
                        Scanner sc1 = new Scanner(System.in);
                        int number1 = sc.nextInt();
                        card = getCarteMain().get(number1);
                    }
                    card.sacrifier(para);
                }
            }

        //Joueur veut poser des croyants au centre de table ou récupérer
        //des croyants
        else if (choix == 4) {
            for (Carte card : this.getCarteMain()) {
                System.out.println(card);
            }
            boolean poser = true;
            while (poser) {
                System.out.println("Vous voulez poser un croyant au centre de table ou " +
                        "récupérer des croyants avec une carte guide?" + "\n" + "0-indiquant poser des croyants"
                        + "\n" + "1-indiquant récupérer des croyants");
                Scanner scanner1 = new Scanner(System.in);
                int answer = scanner1.nextInt();
                while (answer != 0 && answer != 1) {
                    System.out.println("Vous devez entrer 0 ou 1");
                    answer = scanner1.nextInt();
                }
                if (answer == 0) {
                        poser(scanner1);

    public void sacCarteAM(){
        for (Carte carte : getCarteMain()) {
            System.out.println(carte);
        }
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        Carte card = getCarteMain().get(number);
    }

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

    private void recuperer(Scanner scanner) {

    }




    public void poser(Scanner scanner){

        //Joueur veut poser des croyants

        for (Carte card1 : this.getCarteMain()) {
            System.out.println(card1);
        }
        System.out.println("Choisissez un croyant à poser au centre");
        int card = scanner.nextInt();
        Carte crPoser = this.getCarteMain().get(card);
        while (!(crPoser instanceof Croyant)) {
            System.out.println("Vous devez choisir une carte croyant");
            card = scanner.nextInt();
            crPoser = this.getCarteMain().get(card);
        }

        switch (crPoser.getOrigine()){
            case Constants.ORIGINE_JOUR:
                if (this.getPointActTot().getJour() >0 ||this.getPointActTot().getJour() >1){
                System.out.println("Vous voulez utiliser point d'action de jour ou de néant?");

                }

                break;

        }





    }





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
    public int jouer( List<Carte> cartemain){
        for(Iterator it = cartemain.iterator();it.hasNext();){
            Carte key = (Carte)it.next();
            System.out.println(key.getNomCarte());
        }
        System.out.println("quel Carte voulez-vous choisir? Donnez le numero.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;


    }*/
}
