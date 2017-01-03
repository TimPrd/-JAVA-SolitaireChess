package ihm.menus;
import metier.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class JMenuAide extends JMenu implements ActionListener
{

    private JMenuItem regles ;

    public JMenuAide() {
        super("Aide");
        add(this.regles = new JMenuItem("Règle du jeu"    )).addActionListener(this);
        this.regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

    }




    @Override

    public void actionPerformed(ActionEvent evt)
    {
        try
        {
            Controleur.getIhm().reglement();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}

