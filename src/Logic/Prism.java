package Logic;

public class Prism {
    private final double baseArea;
    private final double height;

    public Prism(double baseArea, double height) {
        if (height < 0) throw new IllegalArgumentException("Height cannot be negative");
        this.baseArea = baseArea;
        this.height = height;
    }

    public double getVolume() {
        return baseArea * height;
    }
}