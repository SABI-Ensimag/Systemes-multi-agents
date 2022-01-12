import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.*;


public class Schelling extends Grille {
    /*
    Sous-classe Schelling de la super classe Grille qui admet comme attributs
    supplémentaires:
    -seuil : ce n'est autre que le paramètre K de l'énoncé du problème de Schelling.
    -nbrVide: Le nombre de cases (et donc d'habitations) vides.
    -vides: Une liste chaînée d'objets Point représentants les lignes et colonnes
     des cases vides.
    -videsInit:Une liste chaînée d'objets Point représentants les lignes et colonnes
     des cases vides à l'instant initial.
    */
    private int seuil;
    private int nbrVide;
    private LinkedList<Point> vides;
    private LinkedList<Point> videsInit;

    /*
    Contructeur de la classe Schelling avec un minimum d'attributs saisis.
    */
    public Schelling(int nbrEtat) {
        super(nbrEtat + 1);
        setK(3);
    }

    /*
    Second contructeur de la classe Schelling avec possibilité de saisir plus
    d'attributs.
    */
    public Schelling(int tailleCellule, int largeur, int hauteur, int nbrEtat, int seuil) {
        super(tailleCellule, largeur, hauteur, nbrEtat + 1);
        setK(seuil);
    }

    /*
    Seter fixant les contraintes que doit respecter le paramètre K.
    */
    public void setK(int seuil) {
        if (seuil < 0 || seuil > 7) {
          throw new IllegalArgumentException("Attention: Le seuil doit être compris entre 0 et 7 !");
        }
        this.seuil = seuil;
    }


    /*
    Méthode initEtats permettant d'initialiser aléatoirement les états des cellules,
    en imposant un minimum de 4 cellule blanches aux coins de la grille, correspondants
     à 4 habitations vides afin de pouvoir lancer la simulation.
    */
    @Override
    public void initEtats() {
        this.vides = new LinkedList<Point>();
        this.videsInit = new LinkedList<Point>();
        this.nbrVide = 0;

        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j== nbrHor - 1) || (i == nbrVer - 1 && j == nbrHor - 1) || (i == nbrVer - 1 && j == 0)) {
                    tabEtat[i][j] = 0;
                    tabEtatInit[i][j] = 0;
                    vides.add(new Point(i, j));
                    videsInit.add(new Point(i, j));
                    nbrVide++;
                } else {
                    double etat = Math.random() * (nbrEtat - 0.001);
                    tabEtat[i][j] = (int) etat;
                    tabEtatInit[i][j] = (int) etat;
                    if ((int) etat == 0) {
                        vides.add(new Point(i, j));
                        videsInit.add(new Point(i, j));
                        nbrVide++;
                    }
                }
            }
        }
    }

    /*
    Methode mettreAJourGrille() qui permet de remettre à jour les états des cellules
    en respectant l'algorithme d'évolution du modèle de Schelling comme décrit
    dans l'énoncé.
    */
    @Override
    public void mettreAJourGrille() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                if (tabEtat[i][j] != 0) {
                    int[][] voisinsCellule = voisins(i, j);
                    int compteur = 0;
                    int ligne, colonne;
                    for (int k = 0; k < 8; k++) {
                        ligne = voisinsCellule[k][0]; colonne = voisinsCellule[k][1];
                        if ((tabEtat[ligne][colonne] != 0) && (tabEtat[ligne][colonne] != tabEtat[i][j])) {
                            compteur++;
                        }
                    }

                    if (compteur > seuil) {
                        Point maison = vides.get(0);
                        vides.remove(0);
                        vides.add(new Point(i, j));
                        tabEtat[(int) maison.getX()][(int) maison.getY()] = tabEtat[i][j];
                        tabEtat[i][j] = 0;
                    }
                }
            }
        }
    }

    @Override
    public void reInit() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                tabEtat[i][j] = tabEtatInit[i][j];
            }
        }

        for (int k = 0; k < nbrVide; k++) {
            vides.remove(0);
            vides.add(videsInit.get(k));
        }

    }

}
