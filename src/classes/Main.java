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
        part.piocherDivi();
        part.piocher();
        part.start();
    }
}
