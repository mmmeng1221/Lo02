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

    /**
     *
     * @param nom le nom de ce carte
     * @param capacite L'expliquation du capacite de ce carte
     * @param dogs tous les dogmes de ce carte
     * @param origine l'origine de ce carte
     * @param nbCroyant le nombre de croyants qu'il peut attacher
     * @param sac le fonction a sacrifier de ce carte
     * @return
     */


    public Guide createClerc1(String nom, String capacite, List<Integer> dogs, int origine, int nbCroyant,Sacrifier sac){


        return new Guide(nom,capacite,dogs,origine,nbCroyant,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {


            }
        });}

//Croyant

    /**
     *
     * @param nom le nom de la carte croyant Ermite
     * @param capacite l'explication du capacite de ce carte
     * @param nbcroyant le nombre de croyants qu'il contient
     * @param dogmes tous les dogmes de ce carte
     * @param origine l'origine de ce carte
     * @param sac le fonction de sacrifier de ce carte
     * @return
     */
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

    /**
     *
     * @param nom le nom de la carte croyant Ermite
     * @param capacite l'explication du capacite de ce carte
     * @param nbcroyant le nombre de croyants qu'il contient
     * @param dogmes tous les dogmes de ce carte
     * @param origine l'origine de ce carte
     * @param sac le fonction de sacrifier de ce carte
     * @return
     */
    public Croyant ErmiteorIntegristes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine, Sacrifier sac){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                List<Carte> croyanttemp = new ArrayList<Carte>();
                List<Carte> cartemain = joueurtemp.getCarteMain();
                for(Iterator i = cartemain.iterator();i.hasNext();){
                    Carte key = (Carte) i.next();
                    if(key instanceof Croyant)
                        croyanttemp.add(key);
                }

              croyanttemp.get(joueurtemp.jouer(croyanttemp)).sacrifier(parameters);

            }
        });
    }
    public Croyant GuerriersSaints(String nom, String capacite, int nbcroyant, List<Integer>dogmes, int origine, Sacrifier sac){
        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                List<Carte> guidetemp = parameters.getMyself().getCarteGuide();
                parameters.getMyself().jouer(guidetemp));

            }
        }
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
