package ihm;

import metier.Defi;
import metier.Joueur;
import metier.piece.*;

import metier.Controleur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by pt150881 on 11/06/16.
 */
public class Tablier extends JPanel implements ActionListener
{
    protected JPanel panelPlateau  = new JPanel();
    protected JPanel panelLateral = new JPanel();
    protected JPanel panelHaut = new JPanel();

    protected static Case[][] tPlateau    = new Case[4][4];
    protected Piece    pieceSelect = null;
    private Case     tmp         = null;

    private ArrayList<int[]> tab = null;

    private Color color = new Color(0, 0, 0);

    private int tmpI, tmpJ;
    private boolean bol;

    private JLabel infoPieceSelect;
    private String piece = "";

    private JButton recommencer;
    private JButton precedent;
    private JButton suivant;

    private JLabel infos;
    private String infosDiverses;

    public Tablier()
    {

        this.setLayout(new BorderLayout());

        this.panelPlateau.setLayout(new GridLayout(4, 4));
        this.panelPlateau.setBorder((new LineBorder(Color.black)));

        this.panelHaut.setLayout(new BorderLayout());

        this.infos = new JLabel("Informations : ");

        this.infoPieceSelect = new JLabel("Piece selectionnee : " + this.piece, SwingConstants.CENTER);


        this.panelHaut.add(panelPlateau);
        this.creerPanelLateral();

        this.add(this.infos, "South");

        this.add(this.panelHaut);

        this.setVisible(true);
    }

    //@Tim
    public void creerPanelLateral()
    {
        this.panelLateral.setLayout(new GridLayout(6,1));
        this.panelLateral.setBorder((new LineBorder(Color.black)));


        this.panelLateral.add(this.infoPieceSelect);
        this.panelLateral.add(new JLabel("Defi n° : " + Defi.getDefiActuel(), SwingConstants.CENTER ));
        this.panelLateral.add(new JLabel("Niveau :", SwingConstants.CENTER ));

        this.recommencer = new JButton("Recommencer");
        this.recommencer.addActionListener(this);

        this.precedent = new JButton("Precedent");
        this.precedent.addActionListener(this);
        if(Defi.getDefiActuel() == 1)
        {
            this.setEnabled(false);
        }

        this.suivant = new JButton("Suivant");
        this.suivant.addActionListener(this);

        this.panelLateral.add(this.recommencer);
        this.panelLateral.add(this.precedent);
        this.panelLateral.add(this.suivant);

        this.panelLateral.setPreferredSize(new Dimension(180, 0));

        this.panelHaut.add(panelLateral, "East");

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

        for (int i = 0; i < tPlateau.length; i++)
        {
            for (int j = 0; j < tPlateau.length; j++)
            {
                if (actionEvent.getSource() == tPlateau[i][j])
                {
                    if (pieceSelect == null || pieceSelect.toString().equals("Vide"))
                    {
                        pieceSelect = tPlateau[i][j].getPiece();
                        tmp = tPlateau[i][j];
                        tmpI = i;
                        tmpJ = j;
                        if (!pieceSelect.toString().equals("Vide"))
                        {
                            colorierEchec(pieceSelect.caseEchec(i, j, tPlateau));
                            colorierValide(pieceSelect.caseValide(i, j, tPlateau));
                            tab = pieceSelect.caseValide(i, j, tPlateau);
                        }
                    } else
                    {
                        if (tPlateau[i][j] != tmp)
                        {

                            for (int[] coord : tab)
                            {
                                if (i == coord[0] && j == coord[1])
                                {
                                    tPlateau[i][j].setPiece(pieceSelect);
                                    tmp.setPiece(new Vide());
                                    // @Robin on décrémente le nb de pièces à chaque fois qu'une est bouffée.
                                    Defi.setNbPieces(Defi.getNbPieces() - 1);
                                    Defi.setNbCoupLocal(Defi.getNbCoupLocal()+1);
                                    System.out.println(Controleur.getJoueur().getNbCoup());
                                    if (Defi.getNbPieces() != 1)
                                    {
                                        this.infosDiverses = "Informations : Il faut finir ce defi pour passer au suivant ! Il reste " + Defi.getNbPieces() + " pièces !";
                                        this.infos.setText(this.infosDiverses);
                                    }
                                    else
                                    {
                                        this.infosDiverses = "Informations : Bravo ! Vous pouvez passer au niveau suivant en cliquant sur \"Suivant\"";
                                        this.infos.setText(this.infosDiverses);
                                        Controleur.getJoueur().debloquerDefi(Defi.getDefiActuel());
                                    }
                                }
                            }

                        }
                        pieceSelect = null;
                        setColor();

                    }
                }
                this.refresh(false);
            }
        }
        if (this.pieceSelect != null)
        {
            this.piece = this.pieceSelect.toString();
            this.infoPieceSelect.setText("<html><center>Piece selectionnee : <br/>" + this.piece + "</center><html>");
        }

        if (actionEvent.getSource() == this.recommencer)
        {
            Controleur.getIhm().recommencer();
        }

        if (actionEvent.getSource() == this.precedent)
        {
            if (!Controleur.getIhm().precedent())
            {
                this.infosDiverses = "Informations : Vous êtes déjà sur le premier niveau !";
                this.infos.setText(this.infosDiverses);
            }
        }

        if (actionEvent.getSource() == this.suivant)
        {
            if (Defi.getDefiActuel() < 60)
            {

                if (Controleur.getJoueur().getDefisDebloques(Defi.getDefiActuel()))
                {
                    this.infosDiverses = "Informations : Bravo ! Vous pouvez passer au niveau suivant en cliquant sur \"Suivant\"";
                    this.infos.setText(this.infosDiverses);
                    Controleur.getIhm().suivant();
                }
                else
                {
                    this.infosDiverses = "Informations : Il faut finir ce defi pour passer au suivant ! Il reste " + Defi.getNbPieces() + " pièces !";
                    this.infos.setText(this.infosDiverses);
                }
            }
            else
            {
                if (Controleur.getJoueur().getDefisDebloques(Defi.getDefiActuel()))
                {
                    this.infosDiverses = "Informations : T'es au dernier niveau connard";
                    this.infos.setText(this.infosDiverses);
                }
                else
                {
                    this.infosDiverses = "Informations : Il faut finir ce defi pour passer au suivant ! Il reste " + Defi.getNbPieces() + " pièces !";
                    this.infos.setText(this.infosDiverses);
                }
            }
            try
            {
                Controleur.getJoueur().sauvegarde();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }





    public void creerCase(int i, int j, Piece p, Color color)
    {
        this.color = color;
        Case c = new Case(p);
        this.tPlateau[i][j] =  c;
        c.setBorder((new LineBorder(Color.white)));
        c.setOpaque(true);
        c.addActionListener(this);
        if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
        {
            c.setBackground(Color.WHITE);
        } else
            c.setBackground(color);
        panelPlateau.add(c);
        this.panelHaut.add(panelPlateau);
    }


    public void setColor()
    {
        for (int j = 0; j<4; j++)
            for (int i = 0; i<4; i++)
            {
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
                {
                    tPlateau[i][j].setBackground(Color.WHITE);
                } else
                    tPlateau[i][j].setBackground(color);
            }
    }


    public void colorierEchec(ArrayList<int[]> tabEchec)
    {
        for(int[] coord:tabEchec)
        {
            System.out.println(coord[0]+"    "+coord[1]);
            tPlateau[coord[0]][coord[1]].setBackground(Color.YELLOW);
        }
    }

    public void colorierValide(ArrayList<int[]> tabEchec)
    {
        for(int[] coord:tabEchec)
        {
            tPlateau[coord[0]][coord[1]].setBackground(Color.RED);
        }
    }

    public void refresh(boolean first)
    {
        if(!first)
        {
            for (int i = 0; i < tPlateau.length; i++)
            {
                for (int j = 0; j < tPlateau.length; j++)
                {
                    tPlateau[i][j].setIcon(tPlateau[i][j].getPiece().getImg());
                }
            }
        }
        this.panelPlateau.revalidate();
    }

    public static Case[][] getTPlateau(){return tPlateau;}



}