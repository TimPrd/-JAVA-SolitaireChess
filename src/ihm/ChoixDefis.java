package ihm;

import metier.Controleur;
import metier.Defi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by pt150881 on 17/06/16.
 */
public class ChoixDefis extends JPanel implements ActionListener
{
    private Color color = Color.white;
    private JPanel panelDefis;
    private JButton button;
    private int cpt = 0;

    private File fCustom;
    private static String[] listefichiers;
    private static String[] liste;

    public ChoixDefis(Color color)
    {
        this.setBackground(color);
        panelDefis = new JPanel();
        panelDefis.setLayout(new GridLayout(5, 5));
        panelDefis.setBackground(color);
        panelDefis.setPreferredSize(new Dimension(500, 500));
        this.fCustom =new File("Defis/custom/");


        this.setLayout(new FlowLayout());

        System.out.println(color.getRGB());
        if (color.getRGB() == -5316441)
            cpt = 0;
        if (color.getRGB() == -3750145)
            cpt = 15;
        if (color.getRGB() == -2642177)
            cpt = 30;
        if (color.getRGB() == -1536382)
            cpt = 45;
        if (color.getRGB() == -11382190)
        {
            listerRepertoire(fCustom);
            cpt = liste.length - 1;
            for (int i = 0; i <= cpt; i++)
            {
                System.out.println(i);
                this.button = new JButton(liste[i] + ".txt");
                this.button.setIcon(new ImageIcon("icone/default.png"));
                this.button.setBackground(color.darker());

                if (i == 1)
                    this.button.setBackground(color);

                this.button.setBorder((new LineBorder(color)));
                this.button.addActionListener(this);
                panelDefis.add(button);

            }

        }
        else
        {
            for (int i = 1; i <= 15; i++)
            {
                this.button = new JButton("" + (i + cpt));
                this.button.setIcon(new ImageIcon("icone/" + (i + cpt) + ".png"));

                this.button.setBackground(color);
                if (!Controleur.getJoueur().getDefisDebloques(i + cpt - 1) && !(i + cpt == 1))
                {
                    this.button.setBackground(color.darker());
                }

                this.button.setBorder((new LineBorder(color)));
                this.button.addActionListener(this);
                panelDefis.add(button);
            }
        }
        this.add(panelDefis);
    }

    public static String[] listerRepertoire(File repertoire)
    {
        listefichiers = repertoire.list();
        liste = new String[listefichiers.length];
        int i;
        for (i = 0; i < listefichiers.length; i++)
        {
            if (listefichiers[i].endsWith(".txt") == true)
            {
                liste[i] = (listefichiers[i].substring(0, listefichiers[i].length() - 4));
            }

        }

        return liste;

    }


    @Override
    public void actionPerformed(ActionEvent r)
    {
        for (int i = 0; i < 15; i++)
            if (((JButton) r.getSource()).getText().equals("" + (i+cpt)))
            {
                if (Controleur.getJoueur().getDefisDebloques((i + cpt) - 1) || (i + cpt == 1))
                {
                    Controleur.getIhm().getPanneauActuel().removeAll();
                    Controleur.getIhm().getPanneauActuel().add(new Defi().creerDefi(i+cpt));
                    Controleur.getIhm().getPanneauActuel().revalidate();
                }
            }
        for (int i = 0; i < listefichiers.length; i++)
        {
            if (((JButton) r.getSource()).getText().equals(liste[i] + ".txt"))
            {
                Controleur.getIhm().getPanneauActuel().removeAll();
                Controleur.getIhm().getPanneauActuel().add(new Defi().creerDefi(i + 61));
                Controleur.getIhm().getPanneauActuel().revalidate();
            }
        }
    }
}

