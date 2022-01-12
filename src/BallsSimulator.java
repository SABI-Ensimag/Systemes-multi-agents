import gui.*;
import java.awt.Color;
import java.awt.Point;

public class BallsSimulator implements Simulable {
    /*
    Classe BallsSimulator qui implémente l'interface Simulable, ses attributs sont:
    -nbrBalles  : Le nombre de balles.
    -balles     : Un objet de la classe Balls.
    -diametre   : Le diametre des balles.
    -couleur    : La couleur des balles.
    -dx et dy   : les composantes du vecteur translation choisi.
    -fenetre    : Un objet de la classe GUISimulator qui determine la fenetre de simulation.
    -largeur et hauteur : Les dimensions de notre fenêtre de simulation.
    */
    private int nbrBalles;
    private Balls balles;
    private int diametre;
    private Color couleur;
    private int dx, dy;
    private GUISimulator fenetre;
    private int largeur, hauteur;

    /*
    Constructeur par défaut de la classe BallsSimulator.
    */
    public BallsSimulator() {
        this.nbrBalles = 5;
        this.balles = new Balls();
        this.diametre = 10;
        this.couleur = Color.RED;
        this.largeur = 800;
        this.hauteur = 600;
        this.dx = 1;
        this.dy = 1;
        this.fenetre = new GUISimulator(hauteur, largeur, Color.BLACK);
    }

    /*
    Second constructeur de la classe BallsSimulator permettant de choisir les attributs.
    */
    public BallsSimulator(int nbrBalles, int diametre, Color couleur, int largeur, int hauteur, int dx, int dy) {
        this.nbrBalles = nbrBalles;
        this.couleur = couleur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        setDiameter(diametre);
        this.balles = new Balls(nbrBalles, diametre, largeur, hauteur);
        this.dx = dx;
        this.dy = dy;
        this.fenetre = new GUISimulator(hauteur, largeur, Color.BLACK);
    }

    /*
    Seter du diametre afin d'éviter des valeurs non permises.
    */
    public void setDiameter(int diametre) {
        if (diametre > largeur || diametre > hauteur) {
          throw new IllegalArgumentException("Attention: Le diametre saisi dépasse les limites de la fenêtre !");
        }
        this.diametre = diametre;
    }

    public Point[] getBalls() {
        return balles.getPoints();
    }

    public GUISimulator getWindow() {
        return fenetre;
    }

    /*
    Méthode permettant de remettre à jour la direction de translation à chaque
    fois qu'un contact de notre objet Balls avec les parois de la fenetre a lieu,
    ce qui permet de simuler des rebondissements.
    */
    public void mettreAJourDirection() {
        int[] tabEx = balles.extremes();
        int xMin, xMax, yMin, yMax;
        xMin = tabEx[0]; xMax = tabEx[1];
        yMin = tabEx[2]; yMax = tabEx[3];

        if (dx > 0 && dy > 0) {
            if (xMax >= (largeur - diametre / 2)) {
                dx = -dx;
            } else if (yMax >= (hauteur - diametre / 2)) {
                dy = -dy;
            }
        }

        if (dx > 0 && dy < 0) {
            if (xMax >= (largeur - diametre / 2)) {
                dx = -dx;
            } else if (yMin <= diametre / 2) {
                dy = -dy;
            }
        }

        if (dx < 0 && dy > 0) {
            if (xMin <= diametre / 2) {
                dx = -dx;
            } else if (yMax >= (hauteur - diametre / 2)) {
                dy = -dy;
            }
        }

        if (dx < 0 && dy < 0) {
            if (xMin <= diametre / 2) {
                dx = -dx;
            } else if (yMin <= diametre / 2) {
                dy = -dy;
            }
        }

    }

    /*
    Méthode draw() qui nous permet à la fois de tracer les limites de notre fenêtre,
    et bien sûr de dessiner nos balles dans le plan.
    */
    public void draw(Balls balles) {
        fenetre.addGraphicalElement(new Rectangle(largeur + 2, hauteur / 2 + 1, Color.WHITE, Color.WHITE, 1, hauteur + 2));
        fenetre.addGraphicalElement(new Rectangle(largeur / 2 + 1, hauteur + 2, Color.WHITE, Color.WHITE, largeur + 2, 1));
        for (Point p: balles.getPoints()) {
            fenetre.addGraphicalElement(new Oval((int) p.getX(), (int) p.getY(), couleur, couleur, diametre));
        }
    }

    @Override
    public void next() {
        fenetre.reset();
        mettreAJourDirection();
        balles.translate(dx, dy);
        draw(balles);
    }

    @Override
    public void restart() {
        fenetre.reset();
        balles.reInit();

        draw(balles);
    }
}
