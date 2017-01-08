package VueClasse;

import classes.carte.Carte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;



/**
 * Created by Administrator on 2017/1/6.
 */
public class VueCarte extends JButton {

    public Carte getThiscarte() {
        return thiscarte;
    }

    private Carte thiscarte = null;


    private ActionListener mylistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {




            setVisible(false);

        }
    }

    public VueCarte(Carte c){
        super();
        this.thiscarte = c;
        this.setBounds(new Rectangle(0,0,200,200));
        this.addActionListener(mylistener);
        this.setActionCommand("enable");
        this.setLayout(new BorderLayout());
        Image image = c.getImage();
        Image temp = image.getScaledInstance(this.getWidth(), this.getHeight(), image.SCALE_SMOOTH );
        ImageIcon icon = new ImageIcon(temp);
        this.setIcon(icon);


    }
}
