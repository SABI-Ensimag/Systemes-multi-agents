

//On teste la classe Balls.

public class TestBalls {
  	public static void main(String[] args) {
        Balls balles = new Balls();
        System.out.println(balles.toString());
        balles.translate(3, 10);
        System.out.println(balles.toString());
        balles.reInit();
        System.out.println(balles.toString());
    }
}
