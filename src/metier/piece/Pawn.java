package metier.piece;

import ihm.Case;

import java.util.ArrayList;

/**
 * Created by mj150192 on 13/06/16.
 */
public class Pawn extends Piece
{
    @Override
    public ArrayList<int[]> caseEchec(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();

        if(i > 0 && j < 3) alRet.add(new int[]{i - 1, j+1});
        if(i > 0 && j > 0) alRet.add(new int[]{i - 1, j-1});

        return alRet;
    }

    @Override
    public ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();

        if(i > 0 && j < 3 && !tPlateau[i-1][j+1].getPiece().toString().equals("Vide")
                && !tPlateau[i-1][j+1].getPiece().estInvincible())
            alRet.add(new int[]{i - 1, j+1});

        if(i > 0 && j > 0 && !tPlateau[i-1][j-1].getPiece().toString().equals("Vide")
                && !tPlateau[i-1][j-1].getPiece().estInvincible())
            alRet.add(new int[]{i - 1, j-1});

        return alRet;
    }
}