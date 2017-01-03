package ihm.menus; /**
 * Timothe PARDIEU - INFOG2  Copyright (c) 2016
 * @author timothe.pardieu@et.univ-lehavre.fr
 */

import javax.swing.*;

/**
 * Pemet d'ajouter à la barre de menu tout les sous menus (fichiers, fiche, outils ..)
 */
public class BarreMenu extends JMenuBar {

    public BarreMenu()
    {
        this.add(new JMenuFichier() );
        this.add(new JMenuDefis  () );
        this.add(new JMenuAide   () );
    }
}
