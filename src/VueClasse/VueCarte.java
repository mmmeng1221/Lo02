package VueClasse;

import classes.Part;
import classes.carte.Carte;
import classes.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.awt.Image;



/**
 * Created by Administrator on 2017/1/6.
 */
public class VueCarte extends JButton {

    public Carte getThiscarte() {
        return thiscarte;
    }

    private Carte thiscarte = null;
    private Joueur thisjoueur = null;
    private Part mp = null;


   /* private ActionListener mylistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


            setVisible(false);

        }
    };*/

    public VueCarte(Carte c){
        super();
        this.thiscarte = c;
        Image image = c.getImage();
        this.setBounds(new Rectangle(0,0,100,155));
        /*this.addActionListener(mylistener);*/
        this.setActionCommand("enable");
        this.setLayout(new BorderLayout());

        BufferedImage mybufferedimage = new BufferedImage(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
        Graphics2D g2 = mybufferedimage.createGraphics();

        g2.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), Color.white,null);

       /*BufferedImage temp = new BufferedImage.(this.getWidth(), this.getHeight(), BufferedImage.SCALE_SMOOTH);*/
        ImageIcon icon = new ImageIcon(mybufferedimage);
        this.setIcon(icon);


    }
    public void synchro(Part mp){
        this.mp = mp;
        this.thisjoueur = mp.getListeJouCourant().get(0);
    }
}
