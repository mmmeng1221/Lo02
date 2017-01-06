package VueClasse;

import javax.swing.*;
import java.util.Observer;

/**
 * Created by Administrator on 2017/1/6.
 */
public class MaVueTotale extends JFrame{
    private static final long serialVersionUID = 12345L;

    private JLabel titleLabel = new JLabel("Pandocréon-Divinae by MengZHANG & YuetongZHANG");

    private JPanel inputPanel = new JPanel();

    private JPanel comtagePanel = new JPanel();

    private JPanel cartePanel = new JPanel();

    private JPanel gamePanel = new JPanel();

    private String nomJoueur;

    private JButton boutonDeffausser;
    private JButton boutonCompleter;
    private JButton boutonSacrifier;
    private JButton boutonUtiliser;


    private JMenu[] menus = {
            new JMenu("Options"),new JMenu("Aide")
    };

    private JMenuItem[] items = {
            new JMenuItem("Facile"),new JMenuItem("Introduction"),
            new JMenuItem("Dur")
    };

    /**
     * Constructeur
     */
    public MaVueTotale(){

    }

    /**
     * input
     */
    public void inputPanel(){
        JLabel labelNomJoueur = new JLabel("Nom:");
        JTextField tf_name = new JTextField("Hero",20);
        nomJoueur = tf_name.getText();
        String[] numAI = {"1","2","3","4","5"};
        JLabel j_num = new JLabel("Nombre de joueur virtuel:");
        JComboBox ai = new JComboBox(numAI);
        ai.setEditable(false);
        ai.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox ai = (JComboBox) e.getSource();
                numjoueur = ai.getSelectedIndex()+1  ;
            }
        });

        JButton btn = new JButton("Oui");
        btn.addActionListener(new ActionListener(){
           /* @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(false);
                if (mp.getMode() instanceof ModeRapide)
                    gamePanel();
                else
                    gamePanelAvance();
            }*/
        });

        inputPanel.add(nomJoueur);
        inputPanel.add(tf_name);
        inputPanel.add(j_num);
        inputPanel.add(ai);
        inputPanel.add(btn);
        mycon.add(inputPanel);
    }


}