package classes;

import com.sun.tools.javac.tree.JCTree;

import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
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
    public Guide createMartyr(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant){
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
                Part partie = Part.getPart();
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
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);



            }
        });
    }
    public Croyant createTravailleurs2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);

            }
        });
    }
    public Croyant createTravailleurs3(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
              /*  parameters.getMyself().setPointActTot(parameters.getMyself().getPointActTot());*/
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);

            }
        });
    }

    public Croyant Ermite(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
            for(Iterator it =  parameters.getListotherjoueur().iterator();it.hasNext();){
                Joueur key = (Joueur)it.next();
                System.out.println(key.getNom());
            }
            System.out.println("quel joueur voulez-vous choisir? Donnez le numero.");
                Scanner sc = new Scanner(System.in);
                int i = sc.nextInt();
                parameters.getListotherjoueur().get(i).jouer();
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
                Part partie = Part.getPart();
                partie.setListeJouCourant(parameters.getListotherjoueur());

            }
        });
    }




//Deus-ex
}
