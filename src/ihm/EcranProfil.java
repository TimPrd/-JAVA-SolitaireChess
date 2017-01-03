package ihm;

import ihm.barreOutils.BarreOutils;
import ihm.menus.BarreMenu;
import metier.Controleur;
import metier.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

/**
 * Created by lr151084 on 17/06/16.
 */

public class EcranProfil extends JPanel implements ActionListener
{

    private static String[] listefichiers;
    private static String[] liste;


    private JPanel panelCbValid, panelHaut;

    private final JTextField pseudoJoueur;
    private JButton valider;
    private BufferedImage logo;
    private JLabel labelLogo;

    private JComboBox cPseudos;
    private Color ciel = new Color(119, 181, 254);

    private File profil = new File("profils/");

    private int     nbCoup = 0 ;
    private String  defis  ="";

    public EcranProfil()
    {
        super();
        this.setBackground(ciel);

        this.setLayout(new BorderLayout());

        panelCbValid = new JPanel();
        panelHaut = new JPanel();

        this.panelHaut.setLayout(new BorderLayout());
        this.panelCbValid.setLayout(new GridLayout(1,2));



        try
        {
            logo = ImageIO.read(new File("img/logo.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        labelLogo = new JLabel(new ImageIcon(logo));



        this.pseudoJoueur = new JTextField("Nom du joueur");
        this.cPseudos     = new JComboBox();
        this.valider      = new JButton("Valider");

        this.pseudoJoueur.setColumns(10);

        this.pseudoJoueur.addActionListener(this);
        this.cPseudos.addActionListener(this);
        this.valider.addActionListener(this);

        this.listerFiches(profil);

        this.panelCbValid.add(cPseudos);
        this.panelCbValid.add(valider);

        this.panelHaut.add(pseudoJoueur, "South");
        this.panelHaut.add(labelLogo);

        this.add(panelHaut, "North");
        this.add(panelCbValid, "South");

        this.setVisible(true);
    }


    private String[] listerFiches(File repertoire)
    {
        listefichiers = repertoire.list();
        liste = new String[listefichiers.length];
        for (int i = 0; i < listefichiers.length; i++)
        {
            if (listefichiers[i].endsWith(".txt"))
            {
                liste[i] = (listefichiers[i].substring(0, listefichiers[i].length() - 4));
                this.cPseudos.addItem(liste[i]);
            }
        }
        return liste;

    }

    public void ecritureSauvegarde() throws IOException
    {
        if (!pseudoJoueur.getText().isEmpty())
        {
            Writer writer = new BufferedWriter(new FileWriter("profils/"+pseudoJoueur.getText()+".txt"));
            writer.write("0");
            writer.append("\n");
            writer.write("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            writer.close();
        }
    }

    private void lectureSauvegarde()
    {
        Scanner sc;
        this.nbCoup = 0 ;
        this.defis ="";
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader("profils/"+pseudoJoueur.getText()+".txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        sc = new Scanner(in);
        nbCoup=sc.nextInt();
        defis =sc.next();
        System.out.println(nbCoup+"\n" +defis);

    }


    private boolean ficheExistante()
    {
        for (int i=0; i<listefichiers.length;i++)
            if (pseudoJoueur.getText().equals(cPseudos.getItemAt(i)))
                return true;
        return false;

    }


    @Override
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == this.cPseudos)
        {
            pseudoJoueur.setText(""+ cPseudos.getItemAt(cPseudos.getSelectedIndex()));
            pseudoJoueur.revalidate();

        }
        if (evt.getSource() == this.valider)
        {

            if(ficheExistante())
                lectureSauvegarde();
            else
                try
                {
                    ecritureSauvegarde();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }


            Controleur.setJoueur(new Joueur(pseudoJoueur.getText(),nbCoup,defis));
            Controleur.getIhm().getPanneauActuel().removeAll();
            Controleur.getIhm().getPanneauActuel().add(new MenuPrincipal());
            Controleur.getIhm().setJMenuBar(new BarreMenu());
            Controleur.getIhm().getPanelOutil().add(new BarreOutils());

            Controleur.getIhm().getPanneauActuel().revalidate();
        }
    }
}
