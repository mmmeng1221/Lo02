package classes.joueur;

import classes.Constants;
import classes.carte.Carte;
import classes.carte.Guide;
import classes.carte.Parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 08/12/2016.
 */
public class HardStrategy implements Strategie {
    @Override
    public void jouer(Parameters parameters) {
       List<Carte> mesGuide = new ArrayList<>();
        mesGuide = getCarteValid(parameters.getMyself().getCarteMain(),Guide.class,parameters);

    }
    public <T extends Carte> List<Carte> getCarteValid(List<Carte> cardTot, Class<T> clazz,Parameters parameters){
        List<Carte> cardTemp = new ArrayList<>();
        List<Carte> cardMain = new ArrayList<>();
        cardMain.addAll(cardTot);

        System.out.println(cardMain.size());


        System.out.println(clazz.getName());
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


