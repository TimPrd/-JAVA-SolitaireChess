package metier;

import ihm.Case;
import ihm.ChoixDefis;
import ihm.Tablier;
import metier.piece.*;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by pt150881 on 11/06/16.
 */
public class Defi
{
    private static int num;
    private static int cptPieces;     // @Robin cptPieces
    private static int nbCoupLocal;
    private static String difficulte;
    private Color color = new Color(0, 0, 0);
    private Color cDebut  = new Color(174, 224, 167);
    private Color cInter  = new Color(198,198,255);
    private Color cAvance = new Color(215, 174,255);
    private Color cExpert = new Color(232, 142, 130);
    private Color cCustom = new Color(82, 82, 82);

    private File fCustom;
    String fileName = "";

    public Tablier creerDefi(int num)
    {
        Scanner sc;
        this.num = num;
        Case c;
        Tablier tab;
        tab = new Tablier();

        if (num >60)
        {
            this.fCustom =new File("Defis/custom/");
            System.out.println(ChoixDefis.listerRepertoire(fCustom)[num-61]);
            fileName = "Defis/custom/" + ChoixDefis.listerRepertoire(fCustom)[num-61] +".txt";
        }
        else
            fileName = "Defis/" + num + ".txt";        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        sc = new Scanner(in);
        sc.nextLine();
        difficulte = sc.nextLine();
        if (difficulte.equals("#Debutant"))
            this.color = cDebut;
        if (difficulte.equals("#Intermediaire"))
            this.color = cInter;
        if (difficulte.equals("#Avance"))
            this.color = cAvance;
        if (difficulte.equals("#Expert"))
            this.color = cExpert;
        if (difficulte.equals("#Custom"))
            this.color = cCustom;

        for (int i = 0; i < 4; i++)
        {
            String ligne = sc.next();
            for (int j = 0; j < 4; j++)
            {
                // @Robin si il y a une piece (sauf vide), on incremente cptPieces.
                switch (ligne.charAt(j))
                {
                    case 'C':
                        tab.creerCase(i, j, new Cavalry(), this.color);
                        cptPieces++;
                        break;
                    case 'B':
                        tab.creerCase(i, j, new Bishop(), this.color);
                        cptPieces++;
                        break;
                    case 'R':
                        tab.creerCase(i, j, new Rook(), this.color);
                        cptPieces++;
                        break;
                    case 'K':
                        tab.creerCase(i, j, new King(), this.color);
                        cptPieces++;
                        break;
                    case 'Q':
                        tab.creerCase(i, j, new Queen(), this.color);
                        cptPieces++;
                        break;
                    case 'P':
                        tab.creerCase(i, j, new Pawn(), this.color);
                        cptPieces++;
                        break;
                    case 'O':
                        tab.creerCase(i, j, new Vide(), this.color);
                        break;
                }
                tab.refresh(true);
            }

        }
        return tab;
    }



    public static int getDefiActuel()
    {
        return num;
    }

    // @Robin getNbPieces()
    public static int getNbPieces()
    {
        return cptPieces;
    }

    public static int getNbCoupLocal()
    {
        return nbCoupLocal;
    }

    public static void setNbCoupLocal(int nbCoutLocal)
    {
        Defi.nbCoupLocal = nbCoutLocal;
    }

    // @Robin setNbPieces()
    public static void setNbPieces(int majCpt)
    {
        cptPieces = majCpt;
    }



}