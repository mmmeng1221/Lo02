package classes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by zhangmeng on 10/11/2016.
 */
abstract class Carte {


    private String origine;
    private Set<Integer> dogmes = new HashSet<Integer>();
    private String nomCarte;
    private String capacite;
    public abstract void uticap();

/*    public void test(){
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.contains(Constants.DOGMES_HUMAIN);

    } */

}
