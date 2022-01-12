import gui.GUISimulator;
import java.awt.Color;

//On teste la classe BallsSimulator.

public class TestBallsSimulator {
    public static void main(String[] args) {
        int nbrBalles = 5;
        int diametre = 20;
        Color couleur = Color.RED;
        int dx = 2;
        int dy = 2;
        int largeur = 800;
        int hauteur = 600;

        BallsSimulator simulation = new BallsSimulator(nbrBalles, diametre, couleur, largeur, hauteur, dx, dy);

        simulation.getWindow().setSimulable(simulation);
    }
}
