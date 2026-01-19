package Logic;

public abstract class Polygon { //abstract to use the methods for the other 2 classes by overriding
    private int n;
    private double sideLength;
    private double[] x;
    private double[] y;


    public abstract double getArea();

    public abstract double getPerimeter();
}
