package VueClasse;

import classes.carte.Carte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Administrator on 2017/1/6.
 */
public class VueCarte extends JButton {

    private Carte thiscarte = null;

    private ActionListener mylistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    public VueCarte(Carte c){
        super();
        this.thiscarte = c;
        this.setBounds(new Rectangle(0,0,200,200));
        this.addActionListener(mylistener);
        this.setLayout(new BorderLayout());
        Image image = c.getImage();
        Image temp = image.getScaledInstance(this.getWidth(), this.getHeight(), image.SCALE_SMOOTH );
        ImageIcon icon = new ImageIcon(temp);
        this.setIcon(icon);

    }
}
