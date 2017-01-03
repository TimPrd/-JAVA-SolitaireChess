package metier;

import ihm.Ihm;

/**
 * Created by pt150881 on 11/06/16.
 */
public class Controleur
{
    private static Ihm ihm;
    private static Joueur joueur;

    public static void main(String[] args)
    {
        joueur = new Joueur("");
        ihm =  new Ihm();
    }

    public static  Ihm getIhm()
    {
        return ihm;
    }

    public static Joueur getJoueur()
    {
        return joueur;
    }

    public static void setJoueur(Joueur joueur)
    {
        Controleur.joueur = joueur;
    }
}
