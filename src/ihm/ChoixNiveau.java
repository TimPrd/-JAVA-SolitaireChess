package ihm;
import java.awt.*;
import javax.swing.*;

/**
 * Created by pt150881 on 17/06/16.
 */
public class ChoixNiveau extends JPanel
{
    private Color cDebut  = new Color(174, 224, 167);
    private Color cInter  = new Color(198,198,255);
    private Color cAvance = new Color(215, 174,255);
    private Color cExpert = new Color(232, 142, 130);
    private Color cCustom = new Color(82, 82, 82);


    private JTabbedPane onglet;
    public ChoixNiveau()
    {


        this.setPreferredSize(new Dimension(400, 200));
        this.setLayout(new BorderLayout());

        ChoixDefis[] tPan = {   new ChoixDefis(cDebut), new ChoixDefis(cInter), new ChoixDefis(cAvance),
                                new ChoixDefis(cExpert), new ChoixDefis(cCustom)};


        onglet = new JTabbedPane();

        onglet.add(String.format("%13s", "Débutant"), tPan[0]);
        onglet.add(String.format("%13s","Intermédiaire"), tPan[1]);
        onglet.add(String.format("%13s","Avancé"), tPan[2]);
        onglet.add(String.format("%13s","Expert"), tPan[3]);
        onglet.add(String.format("%13s","Custom"), tPan[4]);


        this.add(onglet);

        this.setVisible(true);

    }

}

