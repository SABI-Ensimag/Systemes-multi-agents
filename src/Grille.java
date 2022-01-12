import gui.*;
import java.awt.Color;
import java.awt.Point;


public abstract class Grille implements Simulable {
    /*
    Cette super classe nommée Grille permet d'initialiser une grille avec
    les attributs suivants:
    -tailleCellule    : La taille d'une cellule (carré) de la grille.
    -nbrHor et nbrVer : Respectivement nombre des colonnes et des lignes.
    -couleurs         : Un tableau contenant les couleurs possibles d'une cellule.
    -tabEtat          : Tableau 2D stockant l'état de chaque cellule.
    -tabEtatInit      : Tableau 2D stockant l'état initial de chaque cellule.
    largeur, hauteur  : Représentent les dimentions de la grille.
    fenetre           : Représente la fenêtre de simulation.
    */
    protected int tailleCellule;
    protected int nbrHor, nbrVer;
    protected int nbrEtat;
    protected Color[] couleurs;
    protected int[][] tabEtat;
    protected int[][] tabEtatInit;
    protected int largeur, hauteur;
    protected GUISimulator fenetre;

    /*
    Contructeur de la classe Grille avec un minimum d'attributs saisis.
    */
    public Grille(int nbrEtat) {
        this.largeur = 600;
        this.hauteur = 600;
        this.tailleCellule = 30;
        this.nbrHor = (int) (this.largeur / this.tailleCellule);
        this.nbrVer = (int) (this.hauteur / this.tailleCellule);
        setNbrEtat(nbrEtat);
        initColors();
        this.tabEtat = new int[this.nbrVer][this.nbrHor];
        this.tabEtatInit = new int[this.nbrVer][this.nbrHor];
        initEtats();
        this.fenetre = new GUISimulator(this.hauteur, this.largeur, Color.BLACK);
    }

    /*
    Second contructeur de la classe Grille, avec plus d'attributs.
    */
    public Grille(int tailleCellule, int largeur, int hauteur, int nbrEtat) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        setSizeCell(tailleCellule);
        this.nbrHor = (int) (largeur / tailleCellule);
        this.nbrVer = (int) (hauteur / tailleCellule);
        setNbrEtat(nbrEtat);
        initColors();
        this.tabEtat = new int[this.nbrVer][this.nbrHor];
        this.tabEtatInit = new int[this.nbrVer][this.nbrHor];
        initEtats();
        this.fenetre = new GUISimulator(hauteur, largeur, Color.BLACK);
    }

    /*
    Seter de la taille de la cellule permettant de respecter la taille de la fenetre.
    */
    public void setSizeCell(int tailleCellule) {
        if (tailleCellule > largeur || tailleCellule > hauteur) {
          throw new IllegalArgumentException("Attention: La fenêtre de simulation ne peut contenir une cellule de cette taille !");
        }
        this.tailleCellule = tailleCellule;
    }

    /*
    Seter permettant de borner le nombre des états, 256 nuances de gris sont permises
    et donc 256 états.
    */
    public void setNbrEtat(int nbrEtat) {
        if (nbrEtat <= 0) {
            throw new IllegalArgumentException("Attention: Le nombre d'états ne peut pas être négatif !");
        } else if (nbrEtat > 256) {
            throw new IllegalArgumentException("Attention: Le nombre d'états ne peut pas dépasser 256 !");
        }
        this.nbrEtat = nbrEtat;
    }

    public GUISimulator getWindow() {
        return fenetre;
    }

    public abstract void initEtats();

    /*
    Méthode InitColors() permettant une initialisation aléatoires des éléments du tableau contenant
    les couleurs, en s'assurant de toujours disposer de la couleur blanche.
    (Dans le cas de deux états possible donc deux couleurs, notre choix s'est porté
    sur une nuance du bleu et le blanc)
    */
    public void initColors() {
        this.couleurs = new Color[nbrEtat];
        if (nbrEtat == 1) {
            this.couleurs[0] = Color.WHITE;
        } else {
            for (int etat = 0; etat < nbrEtat; etat++) {
                int gris = etat * (255 / (nbrEtat - 1));
                this.couleurs[nbrEtat - etat - 1] = new Color(gris, gris, gris);
            }
        }

        if (nbrEtat == 2) {
            this.couleurs[1] = Color.decode("#008b8b");
        }
    }

    public void reInit() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                tabEtat[i][j] = tabEtatInit[i][j];
            }
        }
    }

    /*
    Méthode voisins(i,j) : retourne un tableau contenant les 8 cellules voisines d'une
    cellule donnée située à la i-ème ligne et à la j-ème colonne.
    */
    public int[][] voisins(int i, int j) {
        int[][] voisinsCellule = {{i - 1, j - 1}, {i - 1, j}, {i - 1, (j + 1) % nbrHor},
                                  {i, j - 1}, {i, (j + 1) % nbrHor},
                                  {(i + 1) % nbrVer, j - 1}, {(i + 1) % nbrVer, j}, {(i + 1) % nbrVer, (j + 1) % nbrHor}};

        for (int k = 0; k < 8; k++) {
            if (voisinsCellule[k][0] < 0) {
                voisinsCellule[k][0] += nbrVer;
            }
            if (voisinsCellule[k][1] < 0) {
                voisinsCellule[k][1] += nbrHor;
            }
        }

        return voisinsCellule;
    }

    /*
    Méthode abstraite mettreAJourGrille() permettant de mettre à jour les états des
    cellules, celle-ci sera définie pour chaque simulation en fonction du modèle.
    */
    public abstract void mettreAJourGrille();

    /*
    Methode draw() permettant de dessiner toutes les cases de la grille une par une
    en lisant les états de celle-ci dans le tableau tabEtat.
    Les contours des cases sont représentés en GRIS.
    */
    public void draw() {
        for (int i = 0; i < nbrVer; i++) {
            for (int j = 0; j < nbrHor; j++) {
                Color couleur = couleurs[tabEtat[i][j]];
                Color couleurFrontiere = Color.GRAY;
                fenetre.addGraphicalElement(new Rectangle((int) ((0.5 + j) * tailleCellule),
                                                          (int) ((0.5 + i) * tailleCellule),
                                                          couleurFrontiere,
                                                          couleur,
                                                          tailleCellule));
            }
        }
    }

    @Override
    public void next() {
        fenetre.reset();
        mettreAJourGrille();
        draw();
    }

    @Override
    public void restart() {
        fenetre.reset();
        reInit();
        draw();
    }

}
