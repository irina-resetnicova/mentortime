package org.example.irina.Shapes;

public class Triangle extends Shape {
    double baseOfTriangle;
    double heightOfTriangle;

    public Triangle(String name, double baseOfTriangle, double heightOfTriangle) {
        super(name);
        this.baseOfTriangle = baseOfTriangle;
        this.heightOfTriangle = heightOfTriangle;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "name='" + name + '\'' +
                ", baseOfTriangle=" + baseOfTriangle +
                ", heightOfTriangle=" + heightOfTriangle +
                '}';
    }

    @Override
    public double perimeter() {
        double perimeterOfTriangle = 2 * Math.sqrt(3) * heightOfTriangle;
        return perimeterOfTriangle;
    }

    @Override
    public double area() {
        double areaOfTriangle = (baseOfTriangle * heightOfTriangle) / 2;
        return areaOfTriangle;
    }

    @Override
    public void displayInfo() {
        String info = "Shape:" + "Shape:" + this.getClass().getSimpleName()
                + System.lineSeparator() + "Base: " + baseOfTriangle
                + System.lineSeparator() + "Height: " + heightOfTriangle
                + System.lineSeparator() + "Area: " + area()
                + System.lineSeparator() + "Perimeter: " + perimeter
                + System.lineSeparator();
        System.out.println(info);
    }
}
