package classes;

/**
 * Created by Administrator on 2016/12/6.
 */
public class Tour {


    private int numero;
    private boolean finir;
    private int nbrDejaJouer;
    public static Tour tour = new Tour();
    public void Tour(){

    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isFinir() {
        return finir;
    }

    public void setFinir(boolean finir) {
        this.finir = finir;
    }

    public int getNbrDejaJouer() {
        return nbrDejaJouer;
    }

    public void setNbrDejaJouer(int nbrDejaJouer) {
        this.nbrDejaJouer = nbrDejaJouer;
    }


}
