package classes.carte;

/**
 * Created by Administrator on 2016/12/4.
 */
public class Divinite extends Carte {

    public boolean capUti = true;
    public String nom;
    public String description;
    public String nomcCpacite;

    public Divinite(String nom,String description, String nomCapacite, Sacrifier method){
        this.nom =nom;
        this.description = description;
        this.nomcCpacite = nomCapacite;

    }
    public void uticap(){

    }

    @Override
    public Sacrifier sacrifier(Parameters parameters) {
        return null;
    }
}
