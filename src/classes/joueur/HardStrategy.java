package classes.joueur;

import classes.Constants;
import classes.De;
import classes.Part;
import classes.carte.Carte;
import classes.carte.Croyant;
import classes.carte.Guide;
import classes.carte.Parameters;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 08/12/2016.
 */
public class HardStrategy implements Strategie {
    @Override
    public void jouer(Parameters parameters) {
        Part.getPart().JoueurAjouterPoint(De.getDe().lancer());
        List<Carte> mesGuide;
        mesGuide = getCarteValid(parameters.getMyself().getCarteMain(),Guide.class,parameters);
        List<Guide> mesGuideValid = new ArrayList<>();
        List<Carte> crValid = new ArrayList<>();
        crValid.addAll(Part.getPart().getCroyantCommun());

        if(crValid.size() > 0){
            if(parameters.getMyself().getPointActTot().getJour() > 0||parameters.getMyself().getPointActTot().getNuit() > 0 || parameters.getMyself().getPointActTot().getNeant() > 0)
            {
            for(Carte card : mesGuide) {
                for (Carte cr : crValid) {
                    if (card.compareDogmes(cr.getDogmes())) {
                        ((Guide) card).getCroyantAttache().add((Croyant) cr);
                        parameters.getMyself().getCarteGuide().add((Guide) card);
                        crValid.remove(cr);
                        mesGuideValid.add((Guide) card);
                        mesGuide.remove(card);
                        Part.getPart().getCroyantCommun().remove(cr);
                        parameters.getMyself().getCarteMain().remove(card);
                        System.out.println(this + " récupère un croyant");
                        JOptionPane.showMessageDialog(null, "il récupère un croyant", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                    if (((Guide) card).getCroyantAttache().size() >= ((Guide) card).getNbCroyant()) {
                        break;
                    }
                }
            }

        }else{
            int num = (int)Math.random()*(parameters.getMyself().getCarteMain().size()-1);
                parameters.getMyself().getCarteMain().remove(num);
                System.out.println(this + "a déffaussé une carte");
                JOptionPane.showMessageDialog(null, "il a déffaussé une carte", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
            }
        }else{
            if(parameters.getMyself().getCarteMain().size()>3){
            List<Carte> croyant = new ArrayList<>();
            for(Carte cdMain : parameters.getMyself().getCarteMain()){
                if(cdMain instanceof Guide || cdMain instanceof Croyant){
                    croyant.add(cdMain);
                }
            }
            int num = (int)(Math.random()*croyant.size()-1);
                croyant.get(num).sacrifier(parameters);
                System.out.println(this + "a sacrifié une carte.");
                JOptionPane.showMessageDialog(null, "il a sacrifié une carte.", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
        }else{
            while(parameters.getMyself().getCarteMain().size() < 7){
                parameters.getMyself().getCarteMain().add(Part.getPart().piocher1Carte());
                System.out.println(this + "a pioché des cartes d'action.");
                JOptionPane.showMessageDialog(null, "il a pioché des cartes d'action.", "Joueur Vituel",JOptionPane.PLAIN_MESSAGE);
            }
            }


        }

    }
    public <T extends Carte> List<Carte> getCarteValid(List<Carte> cardTot, Class<T> clazz,Parameters parameters){
        List<Carte> cardTemp = new ArrayList<>();
        List<Carte> cardMain = new ArrayList<>();

        cardMain.addAll(cardTot);

        System.out.println(cardMain.size());


        if (clazz.equals(cardMain.get(0).getClass())){
            System.out.println("true");
        }
        if(parameters.getMyself().getPointActTot().getJour() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_JOUR && card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
            if(parameters.getMyself().getPointActTot().getJour() > 1){
                for(Carte card : cardMain){
                    if(card.getOrigine() == Constants.ORIGINE_NEANT&& card.getClass().equals(clazz)){
                        cardTemp.add(card);
                        cardMain.remove(card);
                    }
                }
            }
        }else if(parameters.getMyself().getPointActTot().getNuit() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_NUIT && card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
            if(parameters.getMyself().getPointActTot().getNuit() > 1){
                for(Carte card : cardMain){
                    if(card.getOrigine() == Constants.ORIGINE_NEANT && card.getClass().equals(clazz)){
                        cardTemp.add(card);
                        cardMain.remove(card);
                    }
                }
            }
        }else if(parameters.getMyself().getPointActTot().getNeant() > 0){
            for(Carte card : cardMain){
                if(card.getOrigine() == Constants.ORIGINE_NEANT&& card.getClass().equals(clazz)){
                    cardTemp.add(card);
                    cardMain.remove(card);
                }
            }
        }
        return cardTemp;
    }
}


