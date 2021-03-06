package VueClasse;

import classes.De;
import classes.Part;
import classes.carte.Carte;
import classes.carte.Divinite;
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
import java.util.*;
import java.util.List;


/**
 * Created by Administrator on 2017/1/6.
 */
public class MaVueTotale extends JFrame{
    private static final long serialVersionUID = 12345L;

    private int startIndex = 0;
    private boolean isEnd = false;

    private JLabel titleLabel = new JLabel("Pandocréon-Divinae by MengZHANG & YuetongZHANG");
    private JLabel noteLabel = new JLabel("Croyant commun :");
    private JLabel noteLabel2 = new JLabel("Croyant recu :");
    private JPanel inputPanel = new JPanel();

    private List<JButton> tousBoutons = new ArrayList<>();
    private List<JButton> boutonsFonctions = new ArrayList<>();
    private List<JButton> listCarteMain = new ArrayList<>();



    private static MaVueTotale maVueTotale  = new MaVueTotale();

    public static MaVueTotale getmaVueTotale(){
    return maVueTotale;
}

    private JPanel panelBouton = new JPanel();

    private JPanel comptagePanel = new JPanel();


    private JPanel carteAMainPanel = new JPanel();


    public JPanel getCroyantCommunPanel() {
        return croyantCommunPanel;
    }

    private JPanel croyantCommunPanel = new JPanel();

    public JPanel getCroyantRecuPanel() {
        return croyantRecuPanel;
    }

    private JPanel croyantRecuPanel = new JPanel();

    private JPanel centerpanel = new JPanel();

    private JPanel dePanel = new JPanel();



    /*    private JPanel gamePanel = new JPanel();

    private Container myContainer = this.getContentPane();*/

    private String nomJoueur;
    private Part part = Part.getPart();
    private int nbrJoueur = 1;

    private JButton boutonDeffausser = new JButton("Déffausser");
    private JButton boutonCompleter = new JButton("Completer");
    private JButton boutonSacrifier = new JButton("Sacrifier");
    private JButton boutonUtiliser = new JButton("Utiliser");
    private static JButton boutonDe = new JButton("De");
    private JButton boutonDivi = new JButton("Divinité");
    private JButton boutonFinir = new JButton("Finir ce tour");

    public JButton getBoutonDe() {
        return boutonDe;
    }

    private JMenu[] menus = {
            new JMenu("Let's play!"),new JMenu("Information")
    };

    private JMenuItem[] items = {
            new JMenuItem("Info"),
            new JMenuItem("Règles du jeu"),
            new JMenuItem("Commencer")
    };

    public void setBoutonInvi(JButton button){
        button.setEnabled(false);
    }
    public void setBoutonV(JButton button){
        button.setEnabled(true);
    }
    
    public void setBoutonsInvi(List<JButton> buttons){
        for(int i = 0; i<buttons.size();i++){
        buttons.get(i).setEnabled(false);
    }}

    public void setBoutonsV(List<JButton> buttons){
        for(int i = 0; i<buttons.size();i++){
            buttons.get(i).setEnabled(true);
        }
    }

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
            String regles = new String("Vous incarnez des Divinités, qui sont caractérisées par leur Origine " + "\n" +
                    "(Jour, Nuit, Aube ou Crépuscule) qui exprime leur filiation, et leurs Dogmes " +"\n" +
                    "(3 parmi : Nature, Humain, Symboles, Mystique, Chaos) qui définissent leurs croyances. " +"\n" +
                    "Chaque Divinité possèdent une capacité spéciale, un pouvoir utilisable une unique fois pendant la partie." +"\n" +
                    "Le but du jeu est d’éliminer les autres Divinités et de prendre la place du Haut Dieu en récupérant " +"\n" +
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





    public JPanel getCenterpanel() {
        return centerpanel;
    }

    /**
     * input
     */
    public void inputPanel(){
       /* JLabel labelNomJoueur = new JLabel("Nom:");
        JTextField tf_name = new JTextField("Hero",20);


        tf_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                part.getListeJouCourant().get(0).setNom(tf_name.getText());
            }
        });
        nomJoueur = tf_name.getText();*/
       part.initialiserJoueur(part);
        String[] numAI = {"1","2","3","4"};
        JLabel j_num = new JLabel("Nombre de joueur virtuel:");
        JComboBox ai = new JComboBox(numAI);
        ai.setEditable(false);
        ai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox ai = (JComboBox) e.getSource();
                nbrJoueur = ai.getSelectedIndex()+1;
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

        boutonDivi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Divinite myDivi = part.getListeJouCourant().get(0).getDivi();
                String string = myDivi.toString();
                string = string + "\n" + "Voulez-vous utiliser la capavité?";
                Object[] options = {"Utiliser ma capacité", "Annuler"};
                String mode = (String) JOptionPane.showInputDialog(null,string, "Divinité",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), options, "Annuler");
                if(mode == "Utiliser ma capacité"){
                    Parameters parameters = new Parameters();
                    parameters.setMyself(part.getListeJouCourant().get(0));
                    parameters.setListotherjoueur(part.getListeJouCourant());
                    myDivi.sacrifier(parameters);
                }


            }

        });



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
                String str = JOptionPane.showInputDialog("Donner votre nom：\n");
                nomJoueur = str;
                part.getListeJouCourant().get(0).setNom(str);
                System.out.print(str);
                Object[] obj = {"facile","dur"};
                String mode = (String) JOptionPane.showInputDialog(null,"Choisir le mode\n", "mode", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj, "facile");
                if(mode == "facile")
                    part.facile(nbrJoueur ,part);
                    else
                        part.dur(nbrJoueur ,part);
                try {

                    setGamePanel();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        /*inputPanel.add(labelNomJoueur);
        inputPanel.add(tf_name);*/
        inputPanel.add(j_num);
        inputPanel.add(ai);
       /* inputPanel.add(modeJv);*/
        inputPanel.add(btn);
        this.getContentPane().add(inputPanel);
    }

    public void setGamePanel() throws IOException {

        part.shuffle();
        part.shuffleDivi();
        part.piocherDivi();
        part.piocher(part.getListeJouCourant());
        setCarteAMainPanel();
        setDePanel();
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
        centerpanel.add(dePanel,BorderLayout.NORTH);
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
        boutonDivi.setBackground(Color.orange);
        boutonDivi.setOpaque(true);
        panelBouton.setLayout(new GridLayout(1,4));
        panelBouton.add(boutonDeffausser);
        boutonDeffausser.addActionListener(deffausser);
        panelBouton.add(boutonCompleter);
        boutonCompleter.addActionListener(completer);
        panelBouton.add(boutonSacrifier);
        boutonSacrifier.addActionListener(sacrifier);
        boutonUtiliser.addActionListener(utiliser);
        panelBouton.add(boutonUtiliser);
        panelBouton.add(boutonDivi);
        boutonFinir.addActionListener(finir);
        panelBouton.add(boutonFinir);

        tousBoutons.add(boutonCompleter);
        tousBoutons.add(boutonDe);
        tousBoutons.add(boutonDeffausser);
        tousBoutons.add(boutonDivi);
        tousBoutons.add(boutonSacrifier);
        tousBoutons.add(boutonUtiliser);
        tousBoutons.add(boutonFinir);

        boutonsFonctions.addAll(tousBoutons);
        boutonsFonctions.remove(boutonDe);

        setBoutonsInvi(boutonsFonctions);
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
            part.getListeJouCourant().get(0).getCarteMain().remove(carte.getThiscarte());

        }
    };

    private ActionListener finir = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(JButton vc : listCarteMain){
                for (ActionListener al : vc.getActionListeners()) {
                    vc.removeActionListener(al);
                }
            }
            setBoutonsInvi(tousBoutons);
            if(startIndex + 1 >= part.getListeJouCourant().size()){
                startIndex = 0;
            }else{
                startIndex = startIndex + 1;
            }
            for(int i = 1; i<=nbrJoueur; i ++){
                Joueur j = part.getListeJouCourant().get(startIndex);
                if( j instanceof JoueurVirtuel){
                    j.jouer();
                }
            }
            boutonDe.setEnabled(true);
        }
    };

    public void start() {
        isEnd = false;
        startIndex=0;
        boolean open;
        while (!isEnd) {
            open = true;
            //JoueurAjouterPoint(De.getDe().lancer());
            Joueur joueur = part.getListeJouCourant().get(startIndex);
            if(joueur instanceof JoueurVirtuel){
                joueur.jouer();}
            if(joueur instanceof JoueurPhysique){
                MaVueTotale.getmaVueTotale().setBoutonV(MaVueTotale.getmaVueTotale().getBoutonDe());
            }
            startotherJour();
            startIndex = startIndex < part.getListeJouCourant().size() -1 ? startIndex + 1 : 0;
            open = false;
        }

    }

    private void startotherJour() {
        int currentIndex;
        currentIndex = startIndex < part.getListeJouCourant().size() -1 ? startIndex + 1 : 0;
        while (currentIndex != startIndex) {
            Joueur joueur = part.getListeJouCourant().get(currentIndex);
            if(joueur instanceof JoueurVirtuel){
                joueur.jouer();}
            if(joueur instanceof JoueurPhysique){
                MaVueTotale.getmaVueTotale().setBoutonV(MaVueTotale.getmaVueTotale().getBoutonDe());
            }
            JOptionPane.showMessageDialog(null, currentIndex + "joueur joue", "Joueur Virtuel",JOptionPane.PLAIN_MESSAGE);
            currentIndex = currentIndex < part.getListeJouCourant().size() -1 ? currentIndex + 1 : 0;
        }
    }

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
                    joueurphysique.getCarteMain().add(carte.getThiscarte());
                    carteAMainPanel.add(carte);
                    JPanel panel = new JPanel();

                 //  MaVueTotale.getmaVueTotale().carteAMainPanel.removeAll();
                 //   setCarteAMainPanel();
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

    private ActionListener utiliser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JoueurPhysique jp = (JoueurPhysique) part.getListeJouCourant().get(0);
            Object[] obj = {"Poser des croyants au centre de la table","Récupérer des croyants",
                    "Utilisez une carte Deux-Ex ou Apocalypse"};
            String mode = (String) JOptionPane.showInputDialog(null,"Choisissez un moyen d'utiliser des cartes\n",
                    "Utiliser", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj,
                    "Poser des croyants au centre de la table");
            int answer;
            if(mode == "Poser des croyants au centre de la table"){
                answer = 0;
                JOptionPane.showMessageDialog(null, "Choisissez un croyant à poser au centre de table", "Poser des croyants",JOptionPane.INFORMATION_MESSAGE);
            } else if(mode == "Récupérer des croyants"){
                answer = 1;

                JOptionPane.showMessageDialog(null, "Choisissez un guide pour récupérer des croyants", "Récupérer des croyants",JOptionPane.INFORMATION_MESSAGE);
            }else{
                answer = 2;
                JOptionPane.showMessageDialog(null, "Choisissez une carte Deux-Ex ou une carte Apocalypse", "Utiliser un Deux-ex ou Apocalypse",JOptionPane.INFORMATION_MESSAGE);
            }
            for(int i=0;i<jp.getCarteMain().size();i++){
                VueCarte carte = (VueCarte) carteAMainPanel.getComponent(i);
                carte.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JoueurPhysique jp = (JoueurPhysique) part.getListeJouCourant().get(0);
                        VueCarte carte = (VueCarte) e.getSource();
                        jp.getCarteMain().remove(carte.getThiscarte());
                        carte.setVisible(false);
                        carteAMainPanel.remove(carte);
                        jp.utiliser(answer,carte);
                    }
                });
            }
        }
    };


    private ActionListener rollDice = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            part.JoueurAjouterPoint(De.getDe().lancer());
            setBoutonsV(boutonsFonctions);
            setBoutonInvi(boutonDe);
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
            listCarteMain.add(carte);
        }

           /* carteAMainPanel.add(panelBouton);*/
    }

    public void setDePanel(){
        boutonDe.addActionListener(rollDice);
        boutonDe.setPreferredSize(new Dimension(100,50));
        dePanel.add(boutonDe);
    }

    public void setCroyantCommunPanel(){
        croyantCommunPanel.setLayout(new GridLayout());
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

        for (int i=1;i<=nbrJoueur;i++){
            String str = "Joueur Virtuel " + i;
            VuePoint computerVue = new VuePoint(str);
            JoueurVirtuel jv = (JoueurVirtuel)part.getListeJouCourant().get(i);
            jv.setNom(str);
            jv.add(computerVue);
            comptagePanel.add(computerVue);
            jv.notifyChanges();

        }
    /*    JOptionPane.showMessageDialog(null, null,"nbrjoueur" + nbrJoueur,JOptionPane.INFORMATION_MESSAGE);*/

        VuePoint myVue = new VuePoint(nomJoueur);
        JoueurPhysique jp = (JoueurPhysique) part.getListeJouCourant().get(0);
        jp.add(myVue);
        jp.setNom("null");
        comptagePanel.add(myVue);
        jp.notifyChanges();
        /*part.getListeJouCourant().get(0);*/


        comptagePanel.setLayout(new GridLayout(nbrJoueur+1,1));

    }
    public void update(Observable arg, Object ob) {

        if (arg instanceof Part) {

            //  int nbr = MaVueTotale.getmaVueTotale().getCroyantCommunPanel().getComponentCount();

            //      for(int i=0;i< nbr;i++){
            MaVueTotale.getmaVueTotale().croyantCommunPanel.removeAll();

            //    }
            for (int i = 0; i < part.croyantCommun.size(); i++) {
                VueCarte vueCarte = new VueCarte(part.croyantCommun.get(i));
                MaVueTotale.getmaVueTotale().croyantCommunPanel.add(vueCarte);
            }


            //  nbr = MaVueTotale.getmaVueTotale().getCroyantRecuPanel().getComponentCount();

            MaVueTotale.getmaVueTotale().getCroyantRecuPanel().removeAll();

            if (part.getListeJouCourant().get(0).getCarteGuide() != null) {
                for (int j = 0; j < part.getListeJouCourant().get(0).getCarteGuide().size(); j++) {
                    if (part.getListeJouCourant().get(0).getCarteGuide().get(j).getCroyantAttache() != null) {
                        for (int i = 0; i < part.getListeJouCourant().get(0).getCarteGuide().get(j).getCroyantAttache().size(); i++) {
                            VueCarte vueCarte = new VueCarte(part.getListeJouCourant().get(0).getCarteGuide().get(j).getCroyantAttache().get(i));
                            MaVueTotale.getmaVueTotale().getCroyantRecuPanel().add(vueCarte);
                        }
                    }
                }
            }

            MaVueTotale.getmaVueTotale().carteAMainPanel.removeAll();


            for (int j = 0; j < part.getListeJouCourant().get(0).getCarteMain().size(); j++) {
                VueCarte vueCarte = new VueCarte(part.getListeJouCourant().get(0).getCarteMain().get(j));
                MaVueTotale.getmaVueTotale().carteAMainPanel.add(vueCarte);
            }


        }


    }

}