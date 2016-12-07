package classes.carte;



import classes.Constants;
import classes.Part;
import classes.joueur.Joueur;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.*;
import java.util.zip.CRC32;


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




        }
        } );
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
                parameters.getMyself().getCarteMain().add(b);
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

    public Croyant cretaeNihillistes(String nom, String capacite, int nbcroyant, List<Integer> dogmes, int origine){

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

    public DeusEx cretaeColereDivine1(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

                    Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                    if(joueurtemp.getCarteGuide() != null ){
                        List<Carte> listguidetemp = new ArrayList<Carte>();
                    for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                        Carte a = (Guide) i.next();
                        if(a.getOrigine() == Constants.ORIGINE_NEANT ||a.getOrigine() == Constants.ORIGINE_NUIT){
                            listguidetemp.add(a);
                        }
                    }
                    if(listguidetemp != null){
                    Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));
                    for(Iterator i = cartetemp.getCroyantAttache().iterator();i.hasNext();){
                        Croyant croyant = (Croyant)i.next();
                        parameters.getPart().croyantCommun.add(croyant);
                        cartetemp.getCroyantAttache().remove(croyant);
                    }

                    parameters.getPart().getCarteDeffause().add(cartetemp);
                    joueurtemp.getCarteGuide().remove(cartetemp);
                }
            }
        }
    });
    }

    public DeusEx cretaeColereDivine2(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {

                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                if(joueurtemp.getCarteGuide() != null ){
                    List<Carte> listguidetemp = new ArrayList<Carte>();
                    for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                        Carte a = (Guide) i.next();
                        if(a.getOrigine() == Constants.ORIGINE_JOUR ||a.getOrigine() == Constants.ORIGINE_NEANT){
                            listguidetemp.add(a);
                        }
                    }
                    if(listguidetemp != null){
                        Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));
                        for(Iterator i = cartetemp.getCroyantAttache().iterator();i.hasNext();){
                            Croyant croyant = (Croyant)i.next();
                            parameters.getPart().croyantCommun.add(croyant);
                            cartetemp.getCroyantAttache().remove(croyant);
                        }

                        parameters.getPart().getCarteDeffause().add(cartetemp);
                        joueurtemp.getCarteGuide().remove(cartetemp);
                    }
                }
            }
        });
    }
    public DeusEx createOrdreCeleste(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));

                while(joueurtemp.getCarteGuide() == null){
                    joueurtemp.afficherfalse();
                    joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                }
                List<Carte> listguidetemp = new ArrayList<Carte>();
                for (Iterator i = joueurtemp.getCarteGuide().iterator(); i.hasNext(); ) {
                    Carte a = (Guide) i.next();
                }
                Guide cartetemp = (Guide) listguidetemp.get(parameters.getMyself().jouer(listguidetemp));
                parameters.getMyself().getCarteGuide().add(cartetemp);
                joueurtemp.getCarteGuide().remove(cartetemp);
            }

        });
    }


    public DeusEx cretaeFourberie(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                boolean avoirdeuxcroyant = false;
                List<Carte> listcroyanttemp = new ArrayList<Carte>();
                Joueur joueurtemp = new Joueur();
                while (avoirdeuxcroyant = false) {
                    listcroyanttemp = null;
                    joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                    for (Iterator i = joueurtemp.getCarteMain().iterator(); i.hasNext(); ) {
                        Carte cartetemp = (Carte) i.next();
                        if (cartetemp instanceof Croyant) {
                            listcroyanttemp.add(cartetemp);
                        }
                    }
                    if (listcroyanttemp.size() < 2) {
                        parameters.getMyself().afficherfalse();
                    } else {
                        avoirdeuxcroyant = true;
                    }
                }
                int num = (int)listcroyanttemp.size();
                Carte a = listcroyanttemp.get(num);
                parameters.getPart().getCarteDeffause().add(a);
                joueurtemp.getCarteMain().remove(a);
                    listcroyanttemp.remove(a);
                int num2 = (int)listcroyanttemp.size();
                Carte b = joueurtemp.getCarteMain().get(num2);
                parameters.getPart().getCarteDeffause().add(b);
                joueurtemp.getCarteMain().remove(b);
                    listcroyanttemp.remove(b);
            }
        });
    }

    public DeusEx createDiversion(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));

                while(joueurtemp.getCarteMain() == null){
                    joueurtemp.afficherfalse();
                    joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                }
                int num = joueurtemp.getCarteMain().size();

                Carte cartetemp = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                parameters.getMyself().getCarteMain().add(cartetemp);
                joueurtemp.getCarteMain().remove(cartetemp);
                num = num - 1;
                Carte cartetemp2 = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                parameters.getMyself().getCarteMain().add(cartetemp2);
                joueurtemp.getCarteMain().remove(cartetemp2);
                num = num - 1;
                Carte cartetemp3 = joueurtemp.getCarteMain().get(parameters.getMyself().jouer(num));
                parameters.getMyself().getCarteMain().add(cartetemp3);
                joueurtemp.getCarteMain().remove(cartetemp3);

            }

        });
    }

    public DeusEx createPhoenix(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
                List<Carte>listcartetemp = new ArrayList<Carte>();
                for(Iterator i = parameters.getMyself().getCarteMain().iterator();i.hasNext();){
                    Carte carte = (Carte)i.next();
                    if(carte instanceof Guide ||carte instanceof Croyant) {
                        listcartetemp.add(carte);
                    }
                }
                if(listcartetemp != null){
                    Carte cartetemp = listcartetemp.get(parameters.getMyself().jouer(listcartetemp));
                    cartetemp.sacrifier(parameters);
                }
                ///这张卡cartetemp， sacrifier之后还应该保留着，怎么处理？？
            }

        });
    }
    /*public DeusEx createInfluenceJour(String nom, String capacite, int origine){

        return new DeusEx(nom,capacite,origine,new Sacrifier(){
            @Override
            public void sacrifier(Parameters parameters) {
             List<Carte>listcartetemp = new ArrayList<Carte>();
                for(Iterator i = parameters.getMyself().getCarteMain().iterator();i.hasNext();){
                    Carte carte = (Carte)i.next();
                    if((carte.getOrigine() = Constants.ORIGINE_NUIT)||(carte.getOrigine() = Constants.ORIGINE_NEANT)) {
                        listcartetemp.add(carte);
                    }
                }

            }

        });
    }*/



    //Divinite
    public Divinite createKillinstred(String nom,String description, String nomCapacite){
        return new Divinite(nom, description, nomCapacite, new Sacrifier() {
            @Override
            public void sacrifier(Parameters parameters) {

                Joueur joueurtemp = parameters.getListotherjoueur().get(parameters.getMyself().jouer(parameters));
                for(Iterator it = joueurtemp.getCarteMain().iterator();it.hasNext();){
                    Carte cartetemp = (Carte)it.next();
                    if(cartetemp instanceof Apocalypse){
                        cartetemp.sacrifier(parameters);
                        break;
                    }



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
