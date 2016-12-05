package classes;

import com.sun.tools.javac.tree.JCTree;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangmeng on 02/12/2016.
 */
public class Factory {
    private static Factory factory = new Factory();
    private Factory(){}
    public static Factory getFactory() {
        return factory;
    }
//Guide
    public Guide createMartyr(){
        Set<Integer> dogMartyr = new HashSet();
        dogMartyr.add(Constants.DOGMES_HUMAIN);
        dogMartyr.add(Constants.DOGMES_NATURE);

        return new Guide("Martyr","Equivalent à la pose d'une carte Apocalypse.", dogMartyr,21,2,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

            }
        });}


//Croyant
    public Croyant createMoins1(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        Parameters parameters = new Parameters();
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
        @Override
                public void sacrifier(Parameters parameters) {
            parameters.
        }
        }
    }
    public Croyant createMoins2(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createMoins3(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_SYMBOLES);
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createMoins4(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_SYMBOLES);
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createMoins5(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Moins", "Donner un point d'action d'origine Jour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createTravailleurs1(){
        //Constants Moins = new Constants;
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_SYMBOLES);
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Travailleurs", "Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createTravailleurs2(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_SYMBOLES);
        return new Croyant("Travailleurs", "Empêche une Divinité possédant le Dogme Chaos ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant createTravailleurs3(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_HUMAIN);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Travailleurs", "Vous piochez deux cartes au hasard dans la main d'une autre Divinité.",2,dogmes,Constants.ORIGINE_JOUR,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }
    public Croyant Ermite(){
        Set<Integer> dogmes = new HashSet<Integer>();
        dogmes.add(Constants.DOGMES_MYSTIQUES);
        dogmes.add(Constants.DOGMES_NATURE);
        dogmes.add(Constants.DOGMES_CHAOS);
        return new Croyant("Ermite", "mpose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.",1,dogmes,Constants.ORIGINE_JOUR,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

            }
        }
    }


//Apocalype
    public Apocalypse creatApo(){
        return new Apocalypse("Apocalypse",0,new Sacrifier(){
            Parameters para = new Parameters();

            @Override
            public void sacrifier(Parameters parameters) {

            }
        });
    }




//Deus-ex
}
