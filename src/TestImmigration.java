import gui.GUISimulator;
import java.awt.Color;

//On teste la classe Immigration.

public class TestImmigration {
    public static void main(String[] args) {
        int tailleCellule = 30;
        int nbrEtat = 4;
        int largeur = 600;
        int hauteur = 600;
        Immigration simulation = new Immigration(tailleCellule, largeur, hauteur, nbrEtat);
        simulation.getWindow().setSimulable(simulation);
    }
}
