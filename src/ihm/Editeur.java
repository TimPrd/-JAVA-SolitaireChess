package ihm;


import metier.piece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by pt150881 on 11/06/16.
 */
//@Tim
public class Editeur extends Tablier implements ActionListener
{
    private JButton valider  ;


    private JLabel lKing   ;
    private JLabel lQueen  ;
    private JLabel lBishop ;
    private JLabel lCavalry;
    private JLabel lRook   ;
    private JLabel lPawn   ;
    private JLabel lVide   ;


    private Piece tmp = new King();

    private int   [] tabInt  ;
    private JLabel[] tabLabel;
    private Case[] tabBouton ;

    private int nb = 0;

    private String nomCustom;
    private JTextField customField;


    Editeur()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.creerCase(i, j, new Vide(), Color.black);
            }
        }

    }

    @Override
    public void creerPanelLateral()
    {
        lKing    = new JLabel();
        lQueen   = new JLabel();
        lBishop  = new JLabel();
        lCavalry = new JLabel();
        lRook    = new JLabel();
        lPawn    = new JLabel();
        lVide    = new JLabel();

        tabInt   = new int   [] {     1,      1 ,       2 ,       2 ,     2,     2,    1      };
        tabLabel = new JLabel[] { lKing, lQueen , lBishop , lCavalry, lRook, lPawn, lVide     };

        for (int i=0; i<tabLabel.length;i++)
            tabLabel[i].setText("X" + tabInt[i]);


        this.panelLateral.setLayout(new BorderLayout());
        this.panelLateral.setBorder((new LineBorder(Color.black)));

        JPanel panelPiece = new JPanel();
        JPanel panelBas = new JPanel();

        panelPiece.setLayout(new FlowLayout());
        panelBas.setLayout(new GridLayout(2,1));

        valider = new JButton("Validation");
        valider.addActionListener(this);

        tabBouton = new Case[7];
        tabBouton[0]= new Case(new King   ());
        tabBouton[1]= new Case(new Queen  ());
        tabBouton[2]= new Case(new Rook   ());
        tabBouton[3]= new Case(new Cavalry());
        tabBouton[4]= new Case(new Bishop ());
        tabBouton[5]= new Case(new Pawn   ());
        tabBouton[6]= new Case(new Vide   ());

        tabBouton[6].setIcon(new ImageIcon("img/Vide.png"));

        for (int i=0; i<tabBouton.length;i++)
        {
            panelPiece.add(tabBouton[i]);
            panelPiece.add(tabLabel [i]);
            this.tabBouton[i].addActionListener(this);
        }

        this.customField = new JTextField("Nom du fichier");

        panelBas.add(customField);
        panelBas.add(valider);


        this.panelLateral.add(panelPiece);
        this.panelLateral.add(panelBas, "South");

        this.panelLateral.setPreferredSize(new Dimension(180,30));

        this.panelHaut.add(panelLateral, "East");

    }


    @Override
    public void actionPerformed(ActionEvent x)
    {
        for (int i = 0; i < tabBouton.length; i++)
            if (x.getSource() == tabBouton[i])
            {
                pieceSelect = tabBouton[i].getPiece();
                nb = i;
            }

        for (Case[] aTPlateau : tPlateau)
            for (int j = 0; j < tPlateau.length; j++)
                if(x.getSource() == aTPlateau[j] && pieceSelect != null &&
                        !aTPlateau[j].getPiece().equals(pieceSelect) && tabInt[nb] > 0)
                {
                    if(!pieceSelect.toString().equals("Vide"))
                    {
                        reduireStock(nb);
                        tabLabel[nb].setText("X" +(tabInt[nb]));
                    }
                    if(!aTPlateau[j].toString().equals("Vide"))
                    {
                        for (int k = 0; k < tabBouton.length; k++)
                            if(aTPlateau[j].toString().equals(tabBouton[k].toString()))
                            {
                                tabInt[k]++;
                                tabLabel[k].setText("X" +tabInt[k]);
                            }
                    }
                    aTPlateau[j].setPiece(pieceSelect);
                }



        this.refresh(false);
        if(x.getSource() == valider)
        {
            nomCustom = customField.getText();
            try
            {
                this.toTxt();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    private void toTxt() throws IOException
    {
        JLabel reussite = new JLabel("Fichier sauvegardé sous le nom : " + nomCustom + "! ");
        JLabel echec    = new JLabel("<html>Fichier non sauvegardé !<br/> Veuillez saisir un nom de fichier et " +
                "n'utilisez pas un nom déjà emprunté.</html>");
        reussite.setAlignmentX(CENTER_ALIGNMENT);

        if(!nomCustom.isEmpty() && !nomExistant())
        {
            Writer writer = new BufferedWriter(new FileWriter("Defis/custom/" + nomCustom + ".txt"));
            writer.write("?Custom \n");
            writer.write("#Custom\n");
            for (int i = 0; i< tPlateau.length ;i++)
            {
                for (int j = 0; j < tPlateau.length; j++)
                {
                    if (tPlateau[i][j].toString().charAt(0) == 'V')
                        writer.write('O');
                    else
                        writer.write(tPlateau[i][j].toString().charAt(0));
                }
                writer.append("\n");
            }
            writer.close();

            JOptionPane.showMessageDialog(null,reussite,"Sauvegarde reussie",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,echec,"Echec de la sauvegarde",JOptionPane.ERROR_MESSAGE);
    }


    private boolean nomExistant()
    {
        File test = new File("Defis/custom/"+nomCustom+".txt");
        return test.exists();
    }


    private void reduireStock(int i)
    {
        tabInt[i]--;
    }




}