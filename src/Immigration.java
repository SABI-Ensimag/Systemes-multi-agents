import gui.*;
import java.awt.Color;
import java.awt.Point;


public class Immigration extends Grille {
    /*
    Sous-classe Immigation de la super classe Grille, admettant comme attribut
    supplémentaire le facteur K, cet ajout permet
    d'effectuer des simulations avec des valeurs de K autres que celles proposées par
    l'énoncé et qui a été fixé à 3.
    */
    private static int K = 3;

    public Immigration(int nbrEtat) {
        super(nbrEtat);
    }

    public Immigration(int tailleCellule, int largeur, int hauteur, int nbrEtat) {
        super(tailleCellule, largeur, hauteur, nbrEtat);
    }

    /*
    Méthode initEtats permettant d'initialiser aléatoirement les états des cellules.
    */
    @Override
    public void initEtats() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                double etat = Math.random() * (nbrEtat - 0.001);
                tabEtat[i][j] = (int) etat;
                tabEtatInit[i][j] = (int) etat;
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
                    if (tabEtat[ligne][colonne] == (tabEtat[i][j] + 1) % nbrEtat) {
                        compteur++;
                    }
                }

                if (compteur >= K) {
                    tmp[i][j] = (tabEtat[i][j] + 1) % nbrEtat;
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
