package Logic;

public class RegularPolygon extends Polygon {
    //data field declaration
    private final int n;
    private final double sideLength;

    public RegularPolygon(int n, double sideLength) {
        if (sideLength == 0 && n < 3)
            throw new IllegalArgumentException("Number of sides must be ≥ 3 and side length must be greater than zero");
        if (sideLength < 0 && n < 3)
            throw new IllegalArgumentException("Number of sides must be ≥ 3 and Side length can not be a negative number");
        if (n < 3) throw new IllegalArgumentException("Number of sides must be ≥ 3");
        if (sideLength < 0) throw new IllegalArgumentException("Side length can not be a negative number");
        if (sideLength == 0) throw new IllegalArgumentException("Side length must be greater than zero");

        this.n = n;
        this.sideLength = sideLength;
    }

    //method for area overridden from polygon class
    @Override
    public double getArea() {
        return (n * Math.pow(sideLength, 2)) / (4.0 * Math.tan(Math.PI / n));
    }

    //method for perimeter
    @Override
    public double getPerimeter() {
        return n * sideLength;
    }
}