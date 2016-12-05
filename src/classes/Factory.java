package classes;

import com.sun.tools.javac.tree.JCTree;

import java.util.*;

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
    public Guide createMartyr(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant,Sacrifier sac){
        return new Guide(nom,capacite,dogs,origine,nbCroyant,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                List<Joueur> joueurCourant =  parameters.getListotherjoueur();
                Joueur min = joueurCourant.get(0);
                for(Joueur j : joueurCourant){
                    if(min.getNbrCro()>j.getNbrCro()){
                        min = j;
                    }
                }
                parameters.getListotherjoueur().remove(min);
                Partie partie = Partie.getPartie();
                partie.setListeJouCourant(parameters.getListotherjoueur());

            }
        });}




    public Guide createClerc1(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant,Sacrifier sac){


        return new Guide(nom,capacite,dogs,origine,nbCroyant,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {


            }
        });}

//Croyant
    public Croyant createMoins(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
        @Override
                public void sacrifier(Parameters parameters) {
            parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);


        }


        });
    }

    public Croyant createTravailleurs1(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){
        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        });
    }
    public Croyant createTravailleurs2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        });
    }
    public Croyant createTravailleurs3(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

            }
        });
    }
    public Croyant Ermite(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));

            }
        });
    }



//Apocalype
    public Apocalypse creatApo(){
        return new Apocalypse("Apocalypse",0,new Sacrifier(){
            Parameters para = new Parameters();

            @Override
            public void sacrifier(Parameters parameters) {
               List<Joueur> joueurCourant =  parameters.getListotherjoueur();
                Joueur min = joueurCourant.get(0);
                for(Joueur j : joueurCourant){
                    if(min.getNbrCro()>j.getNbrCro()){
                        min = j;
                    }
                }
                parameters.getListotherjoueur().remove(min);
                Partie partie = Partie.getPartie();
                partie.setListeJouCourant(parameters.getListotherjoueur());

            }
        });
    }




//Deus-ex
}
