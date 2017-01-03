package ihm;

import ihm.barreOutils.BarreOutils;
import ihm.menus.BarreMenu;
import metier.Controleur;
import metier.Defi;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by pt150881 on 11/06/16.
 */
public class Ihm extends JFrame
{
    private JPanel panelOutil= new JPanel();
    private JPanel panneauActuel = new JPanel();
    private Defi defi = new Defi();
    private static String oS;
    public JPanel getPannelOutils;

    public Ihm()
    {
        this.setTitle("");
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        panelOutil.setLayout(new GridLayout(1,1));

        panneauActuel.setLayout(new BorderLayout());

        panneauActuel.add(new EcranProfil()); //On met EcranProfil en départ



        this.add(panelOutil,"North");
        this.add(panneauActuel);


        this.setVisible(true);

    }

    // @Robin recommencer()
    public void recommencer()
    {
        Defi.setNbPieces(0);
        Defi.setNbCoupLocal(Defi.getNbCoupLocal()+2);
        this.panneauActuel.removeAll();
        this.panneauActuel.add(defi.creerDefi(Defi.getDefiActuel()));
        this.panneauActuel.revalidate();
    }

    // @Robin precedent()
    public boolean precedent()
    {
        if (Defi.getDefiActuel() > 1)
        {
            Defi.setNbPieces(0);
            Defi.setNbCoupLocal(0);
            this.panneauActuel.removeAll();
            this.panneauActuel.add(defi.creerDefi(Defi.getDefiActuel() - 1));
            this.panneauActuel.revalidate();
            return true;
        }
        else
            return false;
    }

    // @Robin suivant()
    public boolean suivant()
    {
        if (Controleur.getJoueur().getDefisDebloques(Defi.getDefiActuel()))
        {
            Defi.setNbPieces(0);
            Controleur.getJoueur().setNbCoup(Controleur.getJoueur().getNbCoup()+Defi.getNbCoupLocal());
            try
            {
                Controleur.getJoueur().sauvegarde();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            Defi.setNbCoupLocal(0);
            this.panneauActuel.removeAll();
            this.panneauActuel.add(defi.creerDefi(Defi.getDefiActuel() + 1));
            this.panneauActuel.revalidate();
            return true;
        }
        else
            return false;
    }

    public void nouvellePartie()
    {
        this.panneauActuel.removeAll();
        Controleur.getJoueur().reset();
        try
        {
            Controleur.getJoueur().sauvegarde();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        this.panneauActuel.add(new EcranProfil());
        this.panneauActuel.revalidate();
    }

    public void pause()
    {

    }

    public void annuler()
    {

    }

    public void reglement() throws IOException
    {
        String regle  = "./regle/regle.html";
        String window = "cmd /c start "                       + regle;
        String linux  = "/etc/alternatives/x-www-browser "    + regle;
        String mac    = "safari "                             + regle;
        oS = System.getProperty("os.name").toLowerCase();

        if(isWindows())
            Runtime.getRuntime().exec(window);
        if(isLinux())
            Runtime.getRuntime().exec(linux);
        if(isMac())
            Runtime.getRuntime().exec(mac);
    }

    public static boolean isWindows()
    {
        return (oS.indexOf("win") >= 0);
    }

    public static boolean isLinux()
    {
        return (oS.indexOf("nix") >= 0 || oS.indexOf("nux") >=0);
    }

    public static boolean isMac()
    {
        return (oS.indexOf("mac") >=0);
    }

    public JPanel getPanneauActuel()
    {
        return panneauActuel;
    }

    public JPanel getPanelOutil() {return panelOutil;}

}
