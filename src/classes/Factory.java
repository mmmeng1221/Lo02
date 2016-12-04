package classes;

import java.util.HashSet;

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

    }

    public Apocalypse creatApo(){
        return new Apocalypse()
    }
}
