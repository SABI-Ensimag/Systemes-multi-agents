import gui.GUISimulator;
import java.awt.Color;

//On teste la classe Conway.

public class TestConway {
    public static void main(String[] args) {
        int tailleCellule = 30;
        int largeur = 600;
        int hauteur = 600;
        Conway simulation = new Conway(tailleCellule, largeur, hauteur);
        simulation.getWindow().setSimulable(simulation);
    }
}
