package metier.piece;

import ihm.Case;

import java.util.ArrayList;

/**
 * Created by aj150336 on 16/06/16.
 */
public class Cavalry extends Piece
{
    public ArrayList<int[]> caseEchec(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();

        if(i < 2 && j > 0) alRet.add(new int[]{i + 2, j-1});
        if(i < 2 && j <3 ) alRet.add(new int[]{i + 2, j+1});
        if(i >=2 && j < 3) alRet.add(new int[]{i - 2, j+1});
        if(i >= 2 && j > 0) alRet.add(new int[]{i - 2, j-1});
        if(i > 0 && j < 2) alRet.add(new int[]{i - 1, j+2});
        if(i > 0 && j >= 2) alRet.add(new int[]{i -1, j-2});
        if(i < 3 && j < 2) alRet.add(new int[]{i + 1, j+2});
        if(i < 3 && j >= 2) alRet.add(new int[]{i + 1, j-2});



        return alRet;
    }

    @Override
    public ArrayList<int[]> caseValide(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();
        if(i < 2 && j > 0 && !tPlateau[i+2][j-1].getPiece().toString().equals("Vide")
                && !tPlateau[i+2][j-1].getPiece().estInvincible())
            alRet.add(new int[]{i + 2, j-1});
        if(i < 2 && j <3 && !tPlateau[i+2][j+1].getPiece().toString().equals("Vide")
                && !tPlateau[i+2][j+1].getPiece().estInvincible())
            alRet.add(new int[]{i + 2, j+1});
        if(i >=2 && j < 3 && !tPlateau[i-2][j+1].getPiece().toString().equals("Vide")
                &&!tPlateau[i-2][j+1].getPiece().estInvincible())
            alRet.add(new int[]{i - 2, j+1});
        if(i >= 2 && j > 0 && !tPlateau[i-2][j-1].getPiece().toString().equals("Vide")
                &&!tPlateau[i-2][j-1].getPiece().estInvincible())
            alRet.add(new int[]{i - 2, j-1});
        if(i > 0 && j < 2 && !tPlateau[i-1][j+2].getPiece().toString().equals("Vide")
                &&!tPlateau[i-1][j+2].getPiece().estInvincible())
            alRet.add(new int[]{i - 1, j+2});
        if(i > 0 && j >= 2 && !tPlateau[i-1][j-2].getPiece().toString().equals("Vide")
                &&!tPlateau[i-1][j-2].getPiece().estInvincible())
            alRet.add(new int[]{i -1, j-2});
        if(i < 3 && j < 2 && !tPlateau[i+1][j+2].getPiece().toString().equals("Vide")
                &&!tPlateau[i+1][j+2].getPiece().estInvincible())
            alRet.add(new int[]{i + 1, j+2});
        if(i < 3 && j >= 2 && !tPlateau[i+1][j-2].getPiece().toString().equals("Vide")
                &&!tPlateau[i+1][j-2].getPiece().estInvincible())
            alRet.add(new int[]{i + 1, j-2});
        return alRet;
    }
}
