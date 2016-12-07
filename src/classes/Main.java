package classes;

/**
 * Created by zhangmeng on 05/12/2016.
 */
public class Main {
    public static void main(String[] args) {

          Part part = Part.getPart();
        part.initialiserCarte();
            part.initialiserJoueur();
            part.shuffle();
            part.piocher();
        while
        for(int i=0;i<part.getListeJouCourant().size();i++){
        part.getListeJouCourant().get(0).jouer();

         }
        part.getTour().getNbrDejaJouer() ++;
    }
}
