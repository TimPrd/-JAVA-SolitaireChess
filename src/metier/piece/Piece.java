package metier.piece;

import ihm.Case;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by pt150881 on 11/06/16.
 */
public abstract class Piece
{
    private String nom;
    protected boolean estInvincible = false;
    protected boolean aObstacle = false;

    public Piece()
    {
        this.nom = this.getClass().getSimpleName();
    }

    @Override
    public String toString()
    {
        return this.nom;
    }

    public abstract ArrayList<int[]> caseEchec (int i, int j, Case[][] tPlateau);
    public abstract ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau);

    public ImageIcon getImg()
    {
        return new ImageIcon("img/"+this.getClass().getSimpleName()+".png");
    }

    public boolean estInvincible(){return this.estInvincible;}

}