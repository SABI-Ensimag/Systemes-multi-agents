import java.awt.Point;

public class Balls {
    /*
    Classe Balls créée avec 6 attributs:
    -nbrBalles    : Le nombre de nbrBalles.
    -diametre     : Le diametre de chaque balles.
    -xMax et yMax : Les bornes limitant respectivement les valeurs des abscisses
                   et des ordonnées des balles.
    -tab          : Tableau qui stocke toutes nos balles sous forme d'objets Point.
    -tabPosInit   : Tableau qui stocke les positions initiales des balles.
    */

    private int nbrBalles;
    private int diametre;
    private int xMax, yMax;
    private Point[] tab;
    private Point[] tabPosInit;

    /*
    Constructeur par défaut de la classe Balls.
    */
    public Balls() {
        this.nbrBalles = 3;
        this.diametre = 10;
        this.xMax = 800;
        this.yMax = 600;
        this.tab = new Point[5];
        this.tabPosInit = new Point[5];
        initBalls();
    }

    /*
    Second constructeur de la classe Balls permettant de choisir les attributs.
    */
    public Balls(int nbrBalles, int diametre, int xMax, int yMax) {
        this.nbrBalles = nbrBalles;
        this.diametre = diametre;
        this.xMax = xMax;
        this.yMax = yMax;
        this.tab = new Point[nbrBalles];
        this.tabPosInit = new Point[nbrBalles];
        initBalls();
    }

    /*
    Accesseur au nombre de balles.
    */
    public int getNbrBalls() {
        return nbrBalles;
    }

    /*
    Accesseur au tableau contenant nos balles.
    */
    public Point[] getPoints() {
        return tab;
    }

    /*
    Méthode initBalls() permettant d'initialiser les positions des balles dans le plan.
    */
    public void initBalls() {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new Point();
            tabPosInit[i] = new Point();
            double x, y;
            x = Math.random() * (xMax - diametre) + diametre / 2;
            y = Math.random() * (yMax - diametre) + diametre / 2;

            tab[i].move((int) x, (int) y);
            tabPosInit[i].move((int) x, (int) y);
        }
    }

    public void translate(int dx, int dy) {
        for (Point p: tab) {
            p.translate(dx, dy);
        }
    }

    public void reInit() {
        int i = 0;
        for (Point p: tab) {
            p.move((int) tabPosInit[i].getX(), (int) tabPosInit[i].getY());
            i++;
        }
    }

    /*
    Methode extremes() permettant de cadrer nos balles en calculant les coordonnées
    minimales et maximales dans le but de les utiliser pour simuler un rebondissement
    sur les bords.
    */
    public int[] extremes() {
        int[] tabEx = {(int) tab[0].getX(), (int) tab[0].getX(), (int) tab[0].getY(), (int) tab[0].getY()};

        for (Point p: tab) {
            if ((int) p.getX() < tabEx[0]) {
                tabEx[0] = (int) p.getX();
            } else if ((int) p.getX() > tabEx[1]) {
                tabEx[1] = (int) p.getX();
            }

            if ((int) p.getY() < tabEx[2]) {
                tabEx[2] = (int) p.getY();
            } else if ((int) p.getY() > tabEx[3]) {
                tabEx[3] = (int) p.getY();
            }
        }

        return tabEx;
    }


    @Override
    public String toString() {
        String chaine = "[";
        for (Point p: tab) {
            chaine = chaine + p.toString() + ", ";
        }

        chaine = chaine.substring(0, chaine.length() - 2);
        chaine = chaine + "]";
        return chaine;
    }
}
