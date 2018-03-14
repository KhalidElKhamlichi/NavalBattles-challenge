package Ships;

public class Clipper extends Ship {

    private final int speedMultiplier = 2;
    public Clipper(int tonsOfDisplacement, int numberOfMasts) {
        super(tonsOfDisplacement, numberOfMasts);
        super.speed *= speedMultiplier;
    }
}
