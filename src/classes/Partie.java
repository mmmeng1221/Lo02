package classes;

import java.util.List;

/**
 * Created by zhangmeng on 04/12/2016.
 */
public class Partie {
    private List<Joueur> listeJou;
    private List<Carte> cartePioche;
    private List<Carte> carteDeffause;
    public List<Carte> croyantCommun;
    private static Partie instance = null;

    private Partie instance(){
        if(instance == null){
            instance = new Partie();
        }
        return instance;

    }


}
