package ihm;

import metier.Controleur;
import metier.Defi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JPanel implements ActionListener
{
    private JButton jouer;
    private JButton editer;
    private JButton tableauScore;
    private Color ciel = new Color(119, 181, 254);
    private Defi defi = new Defi();
    private Editeur editeur = new Editeur();

    public MenuPrincipal()
    {
        super();
        this.setBackground(ciel);
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new GridLayout(3,1));



        this.jouer = new JButton("Jouer");
        this.jouer.addActionListener(this);

        this.editer = new JButton("Editer");
        this.editer.addActionListener(this);


        this.tableauScore = new JButton("Classement");
        this.tableauScore.addActionListener(this);

        this.add(this.jouer);
        this.add(this.editer);
        this.add(this.tableauScore);

        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent evt)
    {

        if (evt.getSource() == this.jouer)

        {
            Controleur.getIhm().getPanneauActuel().removeAll();
            Controleur.getIhm().getPanneauActuel().add(new ChoixNiveau());
            Controleur.getIhm().getPanneauActuel().revalidate();
        }

        if (evt.getSource() == this.editer)

        {
            Controleur.getIhm().getPanneauActuel().removeAll();
            Controleur.getIhm().getPanneauActuel().add(editeur);
            Controleur.getIhm().getPanneauActuel().revalidate();
        }

        if (evt.getSource() == this.tableauScore)
        {
            Controleur.getIhm().getPanneauActuel().removeAll();
            //Controleur.getIhm().getPanneauActuel().add(); //Remettre à Leaderboard
            Controleur.getIhm().getPanneauActuel().revalidate();
        }

    }


}
