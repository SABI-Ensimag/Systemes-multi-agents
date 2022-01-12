import gui.*;
import java.awt.Color;
import java.awt.Point;

public class Conway extends Grille {
    /*
    Sous classe Conway de la super classe Grille, admettant deux attributs supplémentaire:
    -MORTE  : Qui représente l'état d'une cellule morte.
    -VIVANTE: Qui représente l'état d'une cellule vivante.
    */
    private static int MORTE = 0;
    private static int VIVANTE = 1;

    public Conway(int nbrEtat) {
        super(nbrEtat);
    }

    public Conway(int tailleCellule, int largeur, int hauteur) {
        super(tailleCellule, largeur, hauteur, 2);
    }

    /*
    Méthode initEtats permettant d'initialiser aléatoirement les états des cellules.
    Un déséquilibre a été introduit délibérement afin de générer beaucoup plus de
    cellules mortes que de cellules vivantes.
    */
    @Override
    public void initEtats() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                double val = Math.random();
                if (val <= 0.8) {
                    tabEtat[i][j] = MORTE;
                    tabEtatInit[i][j] = MORTE;
                } else {
                    tabEtat[i][j] = VIVANTE;
                    tabEtatInit[i][j] = VIVANTE;
                }
            }
        }
    }

    /*
    Methode mettreAJourGrille() qui permet de remettre à jour les états des cellules
    en respectant l'algorithme d'évolution décrit dans l'énoncé.
    */
    @Override
    public void mettreAJourGrille() {
        int[][] tmp = new int[nbrVer][nbrHor];

        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                int[][] voisinsCellule = voisins(i, j);
                int compteur = 0;
                int ligne, colonne;
                for (int k = 0; k < 8; k++) {
                    ligne = voisinsCellule[k][0]; colonne = voisinsCellule[k][1];
                    if (tabEtat[ligne][colonne] == VIVANTE) {
                        compteur++;
                    }
                }

                if (tabEtat[i][j] == MORTE && compteur == 3) {
                    tmp[i][j] = VIVANTE;
                } else if (tabEtat[i][j] == VIVANTE && (compteur <= 1 || compteur > 3)) {
                    tmp[i][j] = MORTE;
                } else {
                    tmp[i][j] = tabEtat[i][j];
                }
            }
        }

        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                tabEtat[i][j] = tmp[i][j];
            }
        }

    }

}
