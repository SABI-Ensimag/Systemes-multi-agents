import gui.GUISimulator;
import java.awt.Color;

//On teste la classe Schelling.

public class TestSchelling {
    public static void main(String[] args) {
        int tailleCellule = 30;
        int nbrEtat = 3;
        int seuil = 2;
        int largeur = 600;
        int hauteur = 600;
        Schelling simulation = new Schelling(tailleCellule, largeur, hauteur, nbrEtat, seuil);
        simulation.getWindow().setSimulable(simulation);
    }
}
