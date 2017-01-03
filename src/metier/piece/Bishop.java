package metier.piece;

import ihm.Case;

import metier.Dep;

import java.util.ArrayList;

/**
 * Created by aj150336 on 16/06/16.
 */
public class Bishop extends Piece
{
    @Override
    public ArrayList<int[]> caseEchec(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = Dep.deplacementBishop(i,j,tPlateau);
        return alRet;
    }

    @Override
    public ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = Dep.rougeBishop(i,j,tPlateau);
        return alRet;
    }
}