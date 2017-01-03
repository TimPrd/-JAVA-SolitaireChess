package metier;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by lr151084 on 17/06/16.
 */
public class Joueur
{
    private String    nom;
    private int       nbCoup;
    private boolean[] defisDebloques;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.defisDebloques = new boolean[76];
        for (boolean b:this.defisDebloques)
            b = false;
    }

    public Joueur( String nom, int nbCoup, String defis)
    {
        this.nom   =nom;
        this.nbCoup=nbCoup;
        this.defisDebloques = new boolean[76];

        for (int i=0; i<defis.length(); i++)
        {
            if (defis.charAt(i)=='f')
                defisDebloques[i]=false;
            if (defis.charAt(i)=='t')
                defisDebloques[i]=true;

        }

    }

    public void reset()
    {
        this.nbCoup=0;
        for (int i =0; i< this.defisDebloques.length; i++)
        {
            defisDebloques[i] = false;

            //debloquerDefi(i);
        }
    }

    public void sauvegarde() throws IOException
    {
        String sRet = "";
        Writer writer = new BufferedWriter(new FileWriter("profils/" + Controleur.getJoueur().getNom() + ".txt"));
        writer.write("" + Controleur.getJoueur().getNbCoup());
        writer.append("\n");
        for (boolean b : Controleur.getJoueur().getDefisDebloquesEntier())
        {
            System.out.println(b);
            if (b)
            {
                sRet += "t";
            } else
                sRet += "f";
        }
        writer.write(sRet);
        writer.close();
    }

    public boolean getDefisDebloques(int defi)
    {
        return defisDebloques[defi];
    }

    public boolean[] getDefisDebloquesEntier()
    {
        return defisDebloques;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNbCoup(int nbCoup)
    {
        this.nbCoup = nbCoup;
    }

    public int getNbCoup()
    {
        return nbCoup;
    }

    public void debloquerDefi(int defi)
    {
        this.defisDebloques[defi] = true;
    }
}
