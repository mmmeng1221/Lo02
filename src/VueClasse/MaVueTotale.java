package VueClasse;

import classes.De;
import classes.Part;
import classes.carte.Carte;
import classes.carte.Parameters;
import classes.joueur.Joueur;
import classes.joueur.JoueurPhysique;
import classes.joueur.JoueurVirtuel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by Administrator on 2017/1/6.
 */
public class MaVueTotale extends JFrame{
    private static final long serialVersionUID = 12345L;

    private JLabel titleLabel = new JLabel("Pandocréon-Divinae by MengZHANG & YuetongZHANG");
    private JLabel noteLabel = new JLabel("Croyant commun :");
    private JLabel noteLabel2 = new JLabel("Croyant recu :");
    private JPanel inputPanel = new JPanel();

    private static MaVueTotale maVueTotale  = new MaVueTotale();

public static MaVueTotale getmaVueTotale(){
    return maVueTotale;
}

    private JPanel panelBouton = new JPanel();

    private JPanel comptagePanel = new JPanel();

    private JPanel carteAMainPanel = new JPanel();

    private JPanel croyantCommunPanel = new JPanel();

    private JPanel croyantRecuPanel = new JPanel();

    private JPanel centerpanel = new JPanel();
/*    private JPanel gamePanel = new JPanel();

    private Container myContainer = this.getContentPane();*/

    private String nomJoueur;
    private Part part = Part.getPart();
    private int nbrJoueur = 1;

    private JButton boutonDeffausser = new JButton("Deffausser");
    private JButton boutonCompleter = new JButton("Completer");
    private JButton boutonSacrifier = new JButton("Sacrifier");
    private JButton boutonUtiliser = new JButton("Utiliser");
    private JButton boutonDe = new JButton("De");

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
    private MaVueTotale(){
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
       /* String[] modeJeu = {"Facile", "Dur"};
        JLabel mode = new JLabel("Mode : ");
        JComboBox modeJv = new JComboBox(modeJeu);
        modeJv.setEditable(false);
        modeJv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox modeJ = (JComboBox) e.getSource();
                String reponseMode = modeJ.getSelectedItem().toString();
                if(reponseMode.equals("Dur")){
                    part.dur(nbrJoueur - 1,part);
                }else{
                    part.facile(nbrJoueur - 1,part);
                }
            }
        });*/




        JButton btn = new JButton("Confirmer");

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(false);
                try {
                    part.initialiserCarte();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                part.initialiserJoueur(part);
                Object[] obj = {"facile","dur"};
                String mode = (String) JOptionPane.showInputDialog(null,"Choisir le mode\n", "mode", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, "facile");
                if(mode == "facile")
                    part.dur(nbrJoueur - 1,part);
                    else
                        part.facile(nbrJoueur - 1,part);
                try {

                    setGamePanel();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        inputPanel.add(labelNomJoueur);
        inputPanel.add(tf_name);
        inputPanel.add(j_num);
        inputPanel.add(ai);
       /* inputPanel.add(modeJv);*/
        inputPanel.add(btn);
        this.getContentPane().add(inputPanel);
    }

    public void setGamePanel() throws IOException {
        part.initialiserCarte();
        part.shuffle();
        part.shuffleDivi();
        part.piocherDivi();
        part.piocher();
        /*part.start();*/
        setCarteAMainPanel();
        setCroyantCommunPanel();
        setCroyantRecuPanel();
        setComptagePanel();
        setPanelBouton();
        titleLabel.setText("Divinae--Pandocreon");
        noteLabel.setText("Croyant commun :");
        noteLabel2.setText("Croyant recu :");
        titleLabel.setBorder((new LineBorder(new Color(231, 201, 87))));
        noteLabel.setBorder((new LineBorder(Color.BLACK,1,true)));
        noteLabel2.setBorder((new LineBorder(Color.BLACK,1,true)));

       this.setLayout(new BorderLayout());
        centerpanel.setLayout(new BorderLayout());
        centerpanel.setPreferredSize(new Dimension(525,400));
        centerpanel.add(croyantCommunPanel,BorderLayout.CENTER);
        centerpanel.add(panelBouton, BorderLayout.SOUTH);

        titleLabel.setPreferredSize(new Dimension(1250,30));
        comptagePanel.setPreferredSize(new Dimension(175,470));
        croyantRecuPanel.setPreferredSize(new Dimension(525,470));
        carteAMainPanel.setPreferredSize(new Dimension(1250,300));
        this.getContentPane().add(titleLabel,BorderLayout.NORTH);
        this.getContentPane().add(comptagePanel,BorderLayout.WEST);
        this.getContentPane().add(centerpanel,BorderLayout.CENTER);
        this.getContentPane().add(croyantRecuPanel,BorderLayout.EAST);
        this.getContentPane().add(carteAMainPanel,BorderLayout.SOUTH);


       /* myContainer.add(gamePanel);*/
    }

    public void setPanelBouton(){
        panelBouton.setLayout(new GridLayout(1,4));
        panelBouton.add(boutonDeffausser);
        boutonDeffausser.addActionListener(deffausser);
        panelBouton.add(boutonCompleter);
        boutonCompleter.addActionListener(completer);
        panelBouton.add(boutonSacrifier);
        boutonSacrifier.addActionListener(sacrifier);

        panelBouton.add(boutonUtiliser);
    }
    private ActionListener deffausser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Joueur joueurphysique = (Joueur)part.getListeJouCourant().get(0);
            Object[] obj2 = new Object[joueurphysique.getCarteMain().size()];
            for(int i = 0;i < joueurphysique.getCarteMain().size();i++)
            {
                obj2[i] = i+1;
            }
            int nbr = (int) JOptionPane.showInputDialog(null,"Combien de carte voulez vous deffausser?\n", "nombre", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj2, 1);

            for(int i=0;i<joueurphysique.getCarteMain().size();i++){
               VueCarte carte = (VueCarte) carteAMainPanel.getComponent(i);
                carte.addActionListener(cartedefausser);
            }

        }
    };
    private ActionListener cartedefausser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Joueur joueurphysique = (Joueur)part.getListeJouCourant().get(0);
           VueCarte carte = (VueCarte) e.getSource();
           joueurphysique.getCarteMain().remove(carte.getThiscarte());
            carte.setVisible(false);
            carteAMainPanel.remove(carte);

        }
    };

    private ActionListener completer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           /* if(this.getCarteMain().size() >= 7){
                System.out.println("");
            }
            while (this.getCarteMain().size() < 7) {
                this.piocher(Part.getPart().piocher1Carte());
            }
        }*/
            Joueur joueurphysique = (Joueur)part.getListeJouCourant().get(0);
            if(joueurphysique.getCarteMain().size() >= 7)
                JOptionPane.showMessageDialog(null, "Vous avez 7 cartes, vous ne pouvez pas piocher des cartes.", "Erreur",JOptionPane.ERROR_MESSAGE);

            else
                while(joueurphysique.getCarteMain().size() < 7){
                    joueurphysique.piocher(part.getCartePioche().get(0));
                    VueCarte carte =new VueCarte(part.getCartePioche().get(0));
                    carte.synchro(part);
                    carteAMainPanel.add(carte);
                    part.getCartePioche().remove(part.getCartePioche().get(0));
                }

        }
    };

    private ActionListener sacrifier = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Joueur joueurphysique = (Joueur)part.getListeJouCourant().get(0);
            for(int i=0;i<joueurphysique.getCarteMain().size();i++){
                VueCarte carte = (VueCarte) carteAMainPanel.getComponent(i);
                    carte.addActionListener(cartesacrifier);
                }




        }
    };

    private ActionListener cartesacrifier = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Joueur joueurphysique = (Joueur)part.getListeJouCourant().get(0);
            VueCarte carte = (VueCarte) e.getSource();
            Parameters parameters = new Parameters();
            parameters.setMyself(joueurphysique);
            parameters.setListotherjoueur(part.getListeJouCourant());
            parameters.setThisC(carte.getThiscarte());
            carte.getThiscarte().sacrifier(parameters);
            carte.setVisible(false);


        }
    };


    private ActionListener rollDice = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            De.getDe().lancer();
        }
    };

    public void setCarteAMainPanel(){
            Joueur joueurphysique = part.getListeJouCourant().get(0);
            carteAMainPanel.setLayout(new GridLayout(1,7));
            carteAMainPanel.setSize(1000,700);
        for (int i=0;i<7;i++){
            VueCarte carte = new VueCarte(joueurphysique.getCarteMain().get(i));
            carte.synchro(part);
            carteAMainPanel.add(carte);
        }

           /* carteAMainPanel.add(panelBouton);*/
    }

    public void setCroyantCommunPanel(){
        croyantCommunPanel.setLayout(new GridLayout(2,9));
        boutonDe.setPreferredSize(new Dimension(100,50));
        /*croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());*/
        croyantCommunPanel.add(boutonDe);
        /*croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());
        croyantCommunPanel.add(new JLabel());*/
        for(Carte c : part.getCroyantCommun()){
            VueCarte vueCroyant = new VueCarte(c);
            croyantCommunPanel.add(vueCroyant);
        }
    }

    public void setCroyantRecuPanel(){

    }

    public void setComptagePanel(){
        /*String str = new String("Moi ");
        VuePoint computerVue = new VuePoint(str);
        JoueurPhysique jp = (JoueurPhysique) part.getListeJouCourant().get(0);
        jp.add(computerVue);
        jp.notifyChanges();*/

      /*  for (int i=1;i<=numjoueur;i++){
            String str = new String("Computer" + i);
            VueComptage computerVue = new VueComptage(str);
            JoueurVirtuel jv = (JoueurVirtuel)mp.getMode().getJoueur(i);
            computerVue.setMode(mp.getMode());
            jv.add(computerVue);
            jv.notifyChanges();
            comptagePanel.add(computerVue);
        }
        VueComptage myVue = new VueComptage(namejoueur);
        myVue.setMode(mp.getMode());
        mp.getMode().getJoueur(0).add(myVue);
        mp.getMode().getJoueur(0).notifyChanges();
        comptagePanel.setLayout(new GridLayout(numjoueur+1,1));
        comptagePanel.add(myVue);*/
        for (int i=1;i<=nbrJoueur-1;i++){
            String str = new String("Joueur Virtuel " + i);
            VuePoint computerVue = new VuePoint(str);
            JoueurVirtuel jv = (JoueurVirtuel)part.getListeJouCourant().get(i);

            jv.add(computerVue);
            jv.notifyChanges();
            comptagePanel.add(computerVue);
        }
        VuePoint myVue = new VuePoint(nomJoueur);
        JoueurPhysique jp = (JoueurPhysique) part.getListeJouCourant().get(0);
        jp.add(myVue);
        jp.notifyChanges();
        comptagePanel.add(myVue);

        /*part.getListeJouCourant().get(0);*/
        comptagePanel.add(myVue);
        part.getListeJouCourant().get(0).add(myVue);
        part.getListeJouCourant().get(0).notifyChanges();
        comptagePanel.setLayout(new GridLayout(nbrJoueur+1,1));

    }

}