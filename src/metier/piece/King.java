package metier.piece;

import ihm.Case;

import java.util.ArrayList;

/**
 * Created by aj150336 on 16/06/16.
 */
public class King extends Piece
{

    @Override
    public ArrayList<int[]> caseEchec(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();

        if(i < 3) alRet.add(new int[]{i+1,j});
        if(i < 3 && j < 3) alRet.add(new int[]{i+1,j+1});
        if(j < 3) alRet.add(new int[]{i, j+1});
        if(i > 0) alRet.add(new int[]{i-1, j});
        if(i > 0 && j > 0) alRet.add(new int[]{i-1, j-1});
        if(i < 3 && j > 0) alRet.add(new int[]{i+1, j-1});
        if(i > 0 && j < 3) alRet.add(new int[]{i-1, j+1});
        if(j > 0) alRet.add(new int[]{i, j-1});

        return alRet;

    }

    @Override
    public ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();

        if(i < 3 && !tPlateau[i+1][j].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i+1,j});
        if(i < 3 && j < 3 && !tPlateau[i+1][j+1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i+1,j+1});
        if(j < 3 && !tPlateau[i][j+1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i, j+1});
        if(i > 0 && !tPlateau[i-1][j].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i-1, j});
        if(i > 0 && j > 0 && !tPlateau[i-1][j-1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i-1, j-1});
        if(i < 3 && j > 0 && !tPlateau[i+1][j-1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i+1, j-1});
        if(i > 0 && j < 3 && !tPlateau[i-1][j+1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i-1, j+1});
        if(j > 0 && !tPlateau[i][j-1].getPiece().toString().equals("Vide"))
            alRet.add(new int[]{i, j-1});

        return alRet;
    }
    @Override
    public boolean estInvincible(){return true;}
}