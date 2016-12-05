package classes;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurPhysique extends Joueur{

    public void JoueurPhysique(){

    }

    /**
     * joueur physique choisit une carte pour jouer
     */
    public void jouer(){
        System.out.println("Choisissez les cartes pas encore jouées ou les cartes récupérée." + "\n");

        for(Carte carte: getCarteMain()){
            System.out.println(carte);
        }
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();



    }


}
