package Logic;

public class ArbitraryPolygon extends Polygon{
    //data field declaration
    private final double[] x;
    private final double[] y;
    //constructor instantiation  and throw statement
    public ArbitraryPolygon(double[] x, double[] y) {

        //check if the coordinates are not equal or one of them is <3 using .length
        if (x.length != y.length || x.length < 3) {
            // throw statement exception
            throw new IllegalArgumentException("Enter at least 3 vertices");
        }
        //instantiation
        this.x = x;
        this.y = y;
    }
    //method for Area and setter getter method
    @Override
    public double getArea() {
        double sum = 0.0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            sum += x[i] * y[j] - x[j] * y[i];
        }
        return Math.abs(sum) / 2.0;
    }
    @Override
    public double getPerimeter() {
        double perimeter = 0.0;
        int n = x.length;
        for (int i = 0; i < n-1; i++) {
            int j = (i + 1) % n;
            double dist = Math.hypot(x[j] - x[i], y[j] - y[i]);
            perimeter += dist;
        }
        if (perimeter <= 0) throw new IllegalArgumentException("Enter valid vertices");
        return perimeter;
    }
}