package classes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangmeng on 02/12/2016.
 */
public class Factory {



    public Guide createMartyr(){
        HashSet<Integer> dogMartyr = new HashSet();
        dogMartyr.add(Constants.DOGMES_HUMAIN);
        dogMartyr.add(Constants.DOGMES_NATURE);
        return new Guide("Martyr","Equivalent à la pose d'une carte Apocalypse.",dogMartyr,Constants.ORIGINE_JOUR,new Sacrifier(){

            @Override
            public void sacrifier(Parameters parameters) {

            }
        });
    public Croyant createMoins1(){
        //Constants Moins = new Constants;
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
        @Override
                public void sacrifier(Parmas parmas) {

        }
        }
    }
    public Apocalypse creatApo(){
        return new Apocalypse()
    }
}
