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


        }
            if (choix == 4) {

                System.out.println("Choisissez les cartes pas encore jouées ou les cartes récupérée." + "\n" + "0-indiquant les cartes pas " +
                        "encore jouées" + "\n" + "1-indiquant les cartes récupéreés");

                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                //Joueur choisit de jouer des cartes récupérées, c'est-à-dire qu'il veut sacrifier une ou plusieurs
                // cartes récupérées
                if (num == 1) {
                    for (Guide guide : getCarteGuide()) {
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

    }

    public void sacCarteAM(){
        for (Carte carte : getCarteMain()) {
            System.out.println(carte);
        }
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        Carte card = getCarteMain().get(number);
    }

    public int jouer(Parameters parameters){
        for(Iterator it = parameters.getListotherjoueur().iterator(); it.hasNext();){
            Joueur key = (Joueur)it.next();
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


    }
}
