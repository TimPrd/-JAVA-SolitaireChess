package ihm.menus; /**
 * Timothe PARDIEU - INFOG2  Copyright (c) 2016
 * @author timothe.pardieu@et.univ-lehavre.fr
 */
import metier.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JMenuFichier extends JMenu implements ActionListener {

    private JMenuItem nouvellePartie, pause, annuler, quitter;

    /**
     */
    public JMenuFichier() {
        super("Fichier");
        add(this.nouvellePartie = new JMenuItem("Nouvelle partie")).addActionListener(this);
        add(this.pause          = new JMenuItem("Pause"          )).addActionListener(this);
        add(this.annuler        = new JMenuItem("Annulation"     )).addActionListener(this);
        add(this.quitter        = new JMenuItem("Quitter"        )).addActionListener(this);

        //On ajoute les raccourcis clavier à chaque action (nouveau = ctrl+n, quitter = ctrl+q ..)
        this.nouvellePartie .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.pause          .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        this.annuler        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK)); //annuler == ctrl z ??
        this.quitter        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //Action sur fichier > nouvellePartie
        if (evt.getSource() == this.nouvellePartie)
            Controleur.getIhm().nouvellePartie();

        //Action sur fichier > pause
        if (evt.getSource() == this.pause)
        {
            Controleur.getIhm().pause();
        }

        //Action sur fichier > annuler
        if (evt.getSource() == this.annuler)
            Controleur.getIhm().annuler();


        //Action sur fichier > Quitter
        if (evt.getSource() == this.quitter)
            //Quitte le programme
            System.exit(0);

    }
}
