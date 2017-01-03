package metier;

import java.util.ArrayList;
import ihm.Case;

/**
 * Created by aj150336 on 16/06/16.
 */
public class Dep
{
    private int cpt;
    public Dep()
    {
        this.cpt = 0;
    }

    public static ArrayList<int[]> deplacementTour(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();
        boolean aObstacle = false;
        int cpt = j - 1;
        while (cpt >= 0 && !aObstacle)
        {
            if (!tPlateau[i][cpt].getPiece().toString().equals("Vide"))
                aObstacle = true;
            alRet.add(new int[]{i, cpt});
            cpt--;
        }

        aObstacle = false;
        cpt = j + 1;
        while (cpt < 4 && !aObstacle)
        {
            if (!tPlateau[i][cpt].getPiece().toString().equals("Vide"))
                aObstacle = true;
            alRet.add(new int[]{i, cpt});
            cpt++;
        }

        aObstacle = false;
        cpt = i - 1;
        while (cpt >= 0 && !aObstacle)
        {
            if (!tPlateau[cpt][j].getPiece().toString().equals("Vide"))
                aObstacle = true;
            alRet.add(new int[]{cpt, j});
            cpt--;
        }

        aObstacle = false;
        cpt = i + 1;
        while (cpt < 4 && !aObstacle)
        {
            if (!tPlateau[cpt][j].getPiece().toString().equals("Vide"))
                aObstacle = true;
            alRet.add(new int[]{cpt, j});
            cpt++;
        }
        return alRet;
    }

    public static ArrayList<int[]> deplacementBishop(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();
        boolean aObstacle = false;
        int cptX=0, cptY=0;

        cptX= j;
        cptY= i;
        System.out.println("Output : " + cptY + "|" + cptX);
        while(cptX < 3 && cptY > 0 && !aObstacle)
        {
            if(!tPlateau[cptY-1][cptX+1].getPiece().toString().equals("Vide"))
                aObstacle = true;
            cptX++;
            cptY--;
            alRet.add(new int[]{cptY,cptX});
        }
        aObstacle = false;
        cptX= j;
        cptY= i;
        while(cptX >0 && cptY > 0 && !aObstacle)
        {
            if(!tPlateau[cptY-1][cptX-1].getPiece().toString().equals("Vide"))
                aObstacle = true;
            cptX--;
            cptY--;
            alRet.add(new int[]{cptY,cptX});

        }
        aObstacle = false;
        cptX= j;
        cptY= i;
        while(cptX < 3 && cptY < 3 && !aObstacle)
        {
            if(!tPlateau[cptY+1][cptX+1].getPiece().toString().equals("Vide"))
                aObstacle = true;
            cptX++;
            cptY++;
            alRet.add(new int[]{cptY,cptX});
        }
        aObstacle = false;
        cptX= j;
        cptY= i;
        while(cptX > 0 && cptY < 3 && !aObstacle)
        {
            if(!tPlateau[cptY+1][cptX-1].getPiece().toString().equals("Vide"))
                aObstacle = true;
            cptX--;
            cptY++;
            alRet.add(new int[]{cptY,cptX});
        }
        return alRet;
    }

    public  static ArrayList<int[]> rougeBishop(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();
        boolean aObstacle = false;
        int cptX = 0, cptY = 0;

        cptX = j;
        cptY = i;
        while (cptX < 3 && cptY > 0 && !aObstacle)
        {
            cptX++;
            cptY--;
            if (!tPlateau[cptY][cptX].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[cptY][cptX].getPiece().estInvincible())
                    alRet.add(new int[]{cptY, cptX});
                aObstacle = true;
            }

        }
        aObstacle = false;
        cptX = j;
        cptY = i;
        while (cptX > 0 && cptY > 0 && !aObstacle)
        {
            cptX--;
            cptY--;
            if (!tPlateau[cptY][cptX].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[cptY][cptX].getPiece().estInvincible())
                    alRet.add(new int[]{cptY, cptX});
                aObstacle = true;
            }

        }
        aObstacle = false;
        cptX = j;
        cptY = i;
        while (cptX < 3 && cptY < 3 && !aObstacle)
        {
            cptX++;
            cptY++;
            if (!tPlateau[cptY][cptX].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[cptY][cptX].getPiece().estInvincible())
                    alRet.add(new int[]{cptY, cptX});
                aObstacle = true;
            }

        }
        aObstacle = false;
        cptX = j;
        cptY = i;
        while (cptX > 0 && cptY < 3 && !aObstacle)
        {
            cptX--;
            cptY++;
            if (!tPlateau[cptY][cptX].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[cptY][cptX].getPiece().estInvincible())
                    alRet.add(new int[]{cptY, cptX});
                aObstacle = true;
            }

        }
        return alRet;
    }
    public  static ArrayList<int[]> rougeTour(int i, int j, Case[][] tPlateau)
    {
        ArrayList<int[]> alRet = new ArrayList<int[]>();
        boolean aObstacle = false;
        for (int k = j - 1; k >= 0; k--)
        {
            if (!tPlateau[i][k].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[i][k].getPiece().estInvincible())
                    alRet.add(new int[]{i, k});
                aObstacle = true;
            }
        }

        aObstacle = false;
        for (int k = j + 1; k < 4; k++)
        {
            if (!tPlateau[i][k].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[i][k].getPiece().estInvincible())
                    alRet.add(new int[]{i, k});
                aObstacle = true;
            }
        }

        aObstacle = false;
        for (int k = i - 1; k >= 0; k--)
        {
            if (!tPlateau[k][j].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[k][j].getPiece().estInvincible())
                    alRet.add(new int[]{k, j});
                aObstacle = true;
            }
        }

        aObstacle = false;
        for (int k = i + 1; k < 4; k++)
        {
            if (!tPlateau[k][j].getPiece().toString().equals("Vide") && !aObstacle)
            {
                if (!tPlateau[k][j].getPiece().estInvincible())
                    alRet.add(new int[]{k, j});
                aObstacle = true;

            }
        }
        return alRet;
    }
}
