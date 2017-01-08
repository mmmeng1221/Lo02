package VueClasse;

import classes.carte.Carte;
import classes.Part;
import classes.joueur.Joueur;
import classes.joueur.EasyStrategy;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Created by Administrator on 2017/1/6.
 */
public class MaVueTotale extends JFrame{
    private static final long serialVersionUID = 12345L;

    private JLabel titleLabel = new JLabel("Pandocréon-Divinae by MengZHANG & YuetongZHANG");

    private JPanel inputPanel = new JPanel();

    private JPanel panelBouton = new JPanel();

    private JPanel comptagePanel = new JPanel();

    private JPanel carteAMainPanel = new JPanel();

    private JPanel croyantCommunPanel = new JPanel();

    private JPanel croyantRecuPanel = new JPanel();


    private JPanel gamePanel = new JPanel();

    private Container myContainer = this.getContentPane();

    private String nomJoueur;
    private Part part = Part.getPart();
    private int nbrJoueur = 1;

    private JButton boutonDeffausser;
    private JButton boutonCompleter;
    private JButton boutonSacrifier;
    private JButton boutonUtiliser;
    private JButton boutonDe;

    private JMenu[] menus = {
            new JMenu("Let's play!"),new JMenu("Information")
    };

    private JMenuItem[] items = {
            new JMenuItem("Info"),
            new JMenuItem("Règles du jeu"),
            new JMenuItem("Commencer")
    };

    private ActionListener commencer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            titleLabel.setText(((JMenuItem)e.getSource()).getText());
            inputPanel();
        }
    };

    private ActionListener info = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String introduction = new String("Nom du jeu: Divinae" + '\n' +
                    "Par : Meng ZHANG, Yuetong ZHANG" + '\n' +
                    "LO02 projet" + '\n' + "L'association Pandocréon");
            JOptionPane.showMessageDialog(null, introduction,"Info",JOptionPane.INFORMATION_MESSAGE);
        }
    };

    private ActionListener regle = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String regles = new String("Vous incarnez des Divinités, qui sont caractérisées par leur Origine " +
                    "(Jour, Nuit, Aube ou Crépuscule) qui exprime leur filiation, et leurs Dogmes " +
                    "(3 parmi : Nature, Humain, Symboles, Mystique, Chaos) qui définissent leurs croyances. " +
                    "Chaque Divinité possèdent une capacité spéciale, un pouvoir utilisable une unique fois pendant la partie.\n" +
                    "Le but du jeu est d’éliminer les autres Divinités et de prendre la place du Haut Dieu en récupérant " +
                    "les prières d’un maximum de Croyants.");
            Object[] options ={ "J'ai compris!" };
             JOptionPane.showOptionDialog(null, regles, "Règles du jeu",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            // JOptionPane.showMessageDialog(null,regles,"Introduction",JOptionPane.INFORMATION_MESSAGE);
        }
    };
    /**
     * Constructeur
     */
    public MaVueTotale(){
        super("Divinae");
        items[0].addActionListener(info);
        items[1].addActionListener(regle);
        items[2].addActionListener(commencer);

        menus[0].add(items[1]);
        menus[0].add(items[2]);
        menus[1].add(items[0]);

        JMenuBar  mb= new JMenuBar();
        for (JMenu jm : menus){
            mb.add(jm);
        }
        setJMenuBar(mb);
        setLayout(new FlowLayout());
        add(titleLabel);
    }


    /**
     * input
     */
    public void inputPanel(){
        JLabel labelNomJoueur = new JLabel("Nom:");
        JTextField tf_name = new JTextField("Hero",20);
        nomJoueur = tf_name.getText();
        String[] numAI = {"1","2","3","4"};
        JLabel j_num = new JLabel("Nombre de joueur virtuel:");
        JComboBox ai = new JComboBox(numAI);
        ai.setEditable(false);
        ai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox ai = (JComboBox) e.getSource();
                nbrJoueur = ai.getSelectedIndex()+1 ;
            }
        });
        String[] modeJeu = {"Facile", "Dur"};
        JLabel mode = new JLabel("Mode : ");
        JComboBox modeJv = new JComboBox(modeJeu);
        modeJv.setEditable(false);
        modeJv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox modeJ = (JComboBox) e.getSource();
                String reponseMode = modeJ.getSelectedItem().toString();
                if(reponseMode.equals("Dur")){
                    part.dur(nbrJoueur - 1);
                }else{
                    part.facile(nbrJoueur - 1);
                }
            }
        });




        JButton btn = new JButton("Confirmer");

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(false);
                setGamePanel();
            }
        });

        inputPanel.add(labelNomJoueur);
        inputPanel.add(tf_name);
        inputPanel.add(j_num);
        inputPanel.add(ai);
        inputPanel.add(btn);
        myContainer.add(inputPanel);
    }

    public void setGamePanel(){
        //part.initialiserCarte();
        part.initialiserJoueur(nbrJoueur);
        part.shuffle();
        part.shuffleDivi();
        part.piocherDivi();
        part.piocher();
        part.start();
        setCarteAMainPanel();
        setCroyantCommunPanel();
        setCroyantRecuPanel();
        setComptagePanel();


        titleLabel.setBorder((new LineBorder(new Color(231, 201, 87))));
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(titleLabel,BorderLayout.NORTH);
        gamePanel.add(comptagePanel,BorderLayout.WEST);
        gamePanel.add(croyantCommunPanel, BorderLayout.CENTER);
        gamePanel.add(croyantRecuPanel, BorderLayout.CENTER);
        gamePanel.add(carteAMainPanel,BorderLayout.SOUTH);
        myContainer.add(gamePanel);
    }

    public void setPanelBouton(){
        panelBouton.setLayout(new GridLayout(1,4));
        panelBouton.add(boutonDeffausser);
        panelBouton.add(boutonCompleter);
        panelBouton.add(boutonSacrifier);
        panelBouton.add(boutonUtiliser);
    }

    public void setCarteAMainPanel(){
            Joueur joueurPhysique = part.getListeJouCourant().get(0);
            carteAMainPanel.setLayout(new GridLayout(1,7));
        for (int i=1;i<=7;i++){
            VueCarte carte = new VueCarte(joueurPhysique.getCarteMain().get(i));
            carte.synchro(part);
            carteAMainPanel.add(carte);
        }
           setPanelBouton();
            carteAMainPanel.add(panelBouton);
    }

    public void setCroyantCommunPanel(){
        croyantCommunPanel.setLayout(new GridLayout(2,9));
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(boutonDe);
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        for(Carte c : part.getCroyantCommun()){
            VueCarte vueCroyant = new VueCarte(c);
            croyantCommunPanel.add(vueCroyant);
        }
    }

    public void setCroyantRecuPanel(){

    }

    public void setComptagePanel(){
        for (int i=1;i<=nbrJoueur;i++){
            String str = new String("Joueur Virtuel " + i);
            VuePoint computerVue = new VuePoint(str);
            JoueurVirtuel jv = (JoueurVirtuel)part.getListeJouCourant().get(i);
            jv.add(computerVue);
            jv.notifyChanges();
            comptagePanel.add(computerVue);
        }
        VuePoint myVue = new VuePoint(nomJoueur);
        part.getListeJouCourant().get(0);
        part.getListeJouCourant().get(0).notifyChanges();
        comptagePanel.setLayout(new GridLayout(nbrJoueur+1,1));
        comptagePanel.add(myVue);
    }

}