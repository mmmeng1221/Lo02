package classes.carte;

/**
 * Created by zhangmeng on 10/11/2016.
 */
public class Apocalypse extends Carte {
    private Sacrifier sac;
    public Apocalypse(String nom, int origine,Sacrifier sac){}

    @Override
    public Sacrifier sacrifier(Parameters parameters) {
        if (sac != null) {
            sac.sacrifier(parameters);
        }
        return null;
    }


}
