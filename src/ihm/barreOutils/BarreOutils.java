package ihm.barreOutils;
/**
 * Timothe PARDIEU - INFOG2  Copyright (c) 2016
 * @author timothe.pardieu@et.univ-lehavre.fr
 */

import metier.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe permettant la création de la barre d'outils en haut
 */
public class BarreOutils extends JToolBar implements ActionListener
{
    //On définit les boutons avec leurs images associé sur le bouton
    private JButton  nouveau          = new JButton(new ImageIcon("images/nouveau.png"));
    private JButton  pause        = new JButton(new ImageIcon("images/pause.png"));
    private JButton  recommencer        = new JButton();
    private JButton  quit        = new JButton();


    /**
     * Constructeur de la barre d'outils. Initialise les boutons avec les images et associe un actionListener
     */
    public BarreOutils()
    {
        this.add(  nouveau  );
        this.add(  pause    );
        this.add(recommencer);
        this.add(quit);

        this.nouveau    .addActionListener(this);
        this.pause      .addActionListener(this);
        this.recommencer.addActionListener(this);
        this.quit       .addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {

        if (evt.getSource() == this.nouveau)
            Controleur.getIhm().nouvellePartie();

        if (evt.getSource() == this.pause)
            Controleur.getIhm().pause();

        if (evt.getSource() == this.recommencer)
            Controleur.getIhm().pause();

        if(evt.getSource() == this.quit)
            System.exit(0);

    }


}
