package ihm.menus;
import ihm.ChoixNiveau;
import ihm.Ihm;
import metier.Controleur;
import metier.Defi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JMenuDefis extends JMenu implements ActionListener {
    private Ihm ihm;
    private JMenuItem choixDefis ;


    /**
     * On crée le menu avec quatre items permettant de régler le zoom ou la hauteur
     * On y ajoute les raccourcis clavier
     */
    public JMenuDefis() {
        super("Menu");
        add(this.choixDefis = new JMenuItem("Choix du défi"    )).addActionListener(this);
        this.choixDefis.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

    }


    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Defi.setNbPieces(0);
        Controleur.getIhm().getPanneauActuel().removeAll();
        Controleur.getIhm().getPanneauActuel().add(new ChoixNiveau());
        Controleur.getIhm().getPanneauActuel().revalidate();



    }
}