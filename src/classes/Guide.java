package classes;

import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangmeng on 03/12/2016.
 */
public class Guide extends Carte{
    private Sacrifier sac;
    private int nbCroyant;
    private int nbCryAttahce;
    private List<Croyant> croyantAttache = new ArrayList();

    public Guide(String nom, String capacite, Set<Integer> dogs, Constants origine, Sacrifier sac) {
        this.setNomCarte(nom);
        this.sac = sac;
    }

    @Override
    public void sacrifier(Parmas parmas) {


        if (sac != null) {
            sac.sacrifier(parmas);
        }


    }

}
