package classes.carte;



import classes.Part;
import classes.joueur.Joueur;

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
     * @return
     */
    public Croyant createMoins(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
        @Override
                public void sacrifier(Parameters parameters) {
            parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getJour() + 1);


        }


        });
    }

    public Croyant createTravailleurs1(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){
        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {




            }
        });
    }
    public Croyant createTravailleurs2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {


            }
        });
    }
    public Croyant createTravailleurs3(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
              /*  parameters.getMyself().setPointActTot(parameters.getMyself().getPointActTot());*/
              Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                int num = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte a = joueurtemp.getCarteMain().get(num);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(a);
                int num2 = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte b = joueurtemp.getCarteMain().get(num2);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(b);
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
     * @return
     */
    public Croyant creatermiteorIntegristes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

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

    public Croyant Integristes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                List<Carte> guidetemp = new ArrayList<Carte>();
                List<Carte> cartemain = joueurtemp.getCarteMain();
                for(Iterator i = cartemain.iterator();i.hasNext();){
                    Carte key = (Carte) i.next();
                    if(key instanceof Guide)
                        guidetemp.add(key);
                }

                guidetemp.get(joueurtemp.jouer(guidetemp)).sacrifier(parameters);

            }
        });
    }

    public Croyant createGuerriersSaints(String nom, String capacite, int nbcroyant, List<Integer>dogmes, int origine){
        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                List<Carte> listguidetemp = new ArrayList<Carte>();
                for (Iterator i = parameters.getMyself().getCarteGuide().iterator(); i.hasNext(); ) {
                    Carte a = (Guide) i.next();
                }
                Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));

                parameters.getMyself().getCarteMain().add(cartetemp);
                for(Iterator i = cartetemp.getCroyantAttache().iterator();i.hasNext();){
                    Croyant croyant = (Croyant)i.next();
                     parameters.getPart().croyantCommun.add(croyant);
                }
            parameters.getMyself().getCarteGuide().remove(cartetemp);



            }
        });

        }


    public Croyant createDiplomates(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                List<Carte> guidetemp = new ArrayList<Carte>();
                List<Carte> cartemain = joueurtemp.getCarteMain();
                for(Iterator i = cartemain.iterator();i.hasNext();){
                    Carte key = (Carte) i.next();
                    if(key instanceof Guide)
                        guidetemp.add(key);
                }

                guidetemp.get(joueurtemp.jouer(guidetemp)).sacrifier(parameters);

            }
        });
}

    public Croyant createDemons(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getNuit() + 1);


            }


        });
    }

    public Croyant createAlchimistes2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                int num = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte a = joueurtemp.getCarteMain().get(num);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(a);
                int num2 = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte b = joueurtemp.getCarteMain().get(num2);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(b);

            }


        });
    }

    public Croyant createVampire(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
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

    public Croyant createLycanthropes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                List<Carte> listguidetemp = new ArrayList<Carte>();
                for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                    Carte a = (Guide) i.next();
                }
                Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));
                for(Iterator i = cartetemp.getCroyantAttache().iterator();i.hasNext();){
                    Croyant croyant = (Croyant)i.next();
                    parameters.getPart().croyantCommun.add(croyant);
                    cartetemp.getCroyantAttache().remove(croyant);
                }

                parameters.getPart().getCarteDeffause().add(cartetemp);
                joueurtemp.getCarteGuide().remove(cartetemp);



        });
    }
    }

    public Croyant createIllusionnistes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                boolean avoircroyant = true;
                List<Carte> croyanttemp = new ArrayList<Carte>();
                Joueur joueurtemp = new Joueur();
                while(avoircroyant = true) {
                   joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));

                    List<Carte> cartemain = joueurtemp.getCarteMain();
                    for (Iterator i = cartemain.iterator(); i.hasNext(); ) {
                        Carte key = (Carte) i.next();
                        if (key instanceof Croyant)
                            croyanttemp.add(key);
                    }
                    if(croyanttemp == null){
                        avoircroyant = false;
                    }
                }
                croyanttemp.get(joueurtemp.jouer(croyanttemp)).sacrifier(parameters);
            }


        });
    }

    public Croyant createEsprites(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                parameters.getMyself().getPointActTot().setJour(parameters.getMyself().getPointActTot().getNeant() + 1);


            }


        });
    }

    public Croyant createAlienes2(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = new Joueur();
                boolean avoirdeuxcarte = true;
                while (avoirdeuxcarte = true) {
                    joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                    if (joueurtemp.getCarteMain().size() < 2) {
                        avoirdeuxcarte = false;
                        joueurtemp.afficherfalse();
                    }
                }
                int num = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte a = joueurtemp.getCarteMain().get(num);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(a);
                int num2 = (int)Math.random()*joueurtemp.getCarteMain().size();
                Carte b = joueurtemp.getCarteMain().get(num2);
                parameters.getMyself().getCarteMain().add(a);
                joueurtemp.getCarteMain().remove(b);

            }
        });
    }

    public Croyant createRevolutionnaires(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

        return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                int nbr = parameters.getMyself().jouer(parameters.getListotherjoueur().size());
                List<Joueur>listjoueurtemp = parameters.getMyself().jouer(nbr,parameters.getListotherjoueur());
                for(Iterator i = listjoueurtemp.iterator();i.hasNext();){
                    Joueur joueurtemp = (Joueur)i.next();
                    int cartenbr = joueurtemp.jouer(joueurtemp.getCarteMain());
                    parameters.getListotherjoueur().add(parameters.getMyself());
                    parameters.setMyself(joueurtemp);
                    parameters.getListotherjoueur().remove(joueurtemp);
                    joueurtemp.getCarteMain().get(cartenbr).sacrifier(parameters);//zhe li de parameters xu yao xiu gai ma?
                }
            }
        });
    }






    public Croyant zhizhang(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

         return new Croyant(nom,capacite,nbcroyant,dogmes,origine,new Sacrifier(){
         @Override
            public void sacrifier(Parameters parameters) {
            System.out.println("zhe zhang pai mei yong!!!");

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
