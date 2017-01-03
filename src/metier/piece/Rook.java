package metier.piece;

import ihm.Case;
import metier.Dep;

import java.util.ArrayList;

/**
 * Created by aj150336 on 16/06/16.
 */
public class Rook extends Piece
{
    int cpt=0;

    @Override
    public ArrayList<int[]> caseEchec(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = Dep.deplacementTour(i, j, tPlateau);
        return alRet;
    }

    @Override
    public ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = Dep.rougeTour(i,j,tPlateau);

        return alRet;
    }
}