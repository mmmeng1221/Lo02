package classes;

import VueClasse.MaVueTotale;
import VueClasse.SwingConsole;

import java.io.IOException;

/**
 * Created by zhangmeng on 05/12/2016.
 */
public class Main {
    public static void main(String[] args) /*throws IOException */{

        /*Part part = Part.getPart();
        part.initialiserCarte();
        part.initialiserJoueur();
        part.shuffle();
        part.shuffleDivi();
        part.piocherDivi();
        part.piocher();
        part.start();*/
        SwingConsole.run(new MaVueTotale(),1250,800);
    }
}
