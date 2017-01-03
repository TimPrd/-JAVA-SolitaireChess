package ihm;

import metier.piece.Piece;

import javax.swing.*;

/**
 * Created by pt150881 on 11/06/16.
 */
public class Case extends JButton
{
    private Piece piece;

    public Case(Piece p)
    {
        super();
        this.piece = p;
        this.setIcon(p.getImg());
    }

    public Piece getPiece()
    {
       return this.piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    @Override
    public String toString()
    {
        return this.piece.toString();
    }



}
