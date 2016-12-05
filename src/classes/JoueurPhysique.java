package classes;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/5.
 */
public class JoueurPhysique extends Joueur{

    public void JoueurPhysique(){

    }
    @Override
    public int jouer(Parameters parameters){
        for(Iterator it = parameters.getListotherjoueur().iterator(); it.hasNext()){
            Joueur key = (Joueur)it.next();
            System.out.println(key.getNom());
        }
        System.out.println("quel joueur voulez-vous choisir? Donnez le numero.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;

    }
    public Carte jouer(Parameters parameters){

    }


}
