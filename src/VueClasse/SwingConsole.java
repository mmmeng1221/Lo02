package VueClasse;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * Created by Administrator on 2017/1/6.
 */
public class SwingConsole {
    public static void run(final JFrame f, final int width, final int height){
    SwingUtilities.invokeLater(new Runnable(){

        @Override
        public void run() {

            JFrame frame = new MaVueTotale();
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setSize(width, height);

        }

    });
}
}
