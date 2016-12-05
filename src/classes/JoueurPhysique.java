package classes;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurPhysique extends Joueur {

    public void JoueurPhysique() {

    }

    /**
     * joueur physique choisit une carte pour jouer
     */
    public void jouer() {
        System.out.println("Choisissez les cartes pas encore jouées ou les cartes récupérée." + "\n" + "0-indiquant les cartes pas " +
                "encore jouées" +"\n" +"1-indiquant les cartes récupéreés");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num == 1) {
            for (Carte carte : getCarteGuide()) {
                System.out.println(carte);
            }
            System.out.println("Choississez une carte guide");
        } else if (num == 0) {

            for (Carte carte : getCarteMain()) {
                System.out.println(carte);
            }
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();


        }}


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
