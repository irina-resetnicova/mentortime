package org.example.irina.Shapes;

public class Circle extends Shape {

    double radiusOfCircle;

    public Circle(String name, double radiusOfCircle) {
        super(name);
        this.radiusOfCircle = radiusOfCircle;
    }



    @Override
    public String toString() {
        return "Circle{" +
                " name='" + name + '\'' +
                ", radiusOfCircle=" + radiusOfCircle +
                '}';
    }

    @Override
    public double perimeter() {
        double perimeterOfCircle = 2 * Math.PI * radiusOfCircle;
        return perimeterOfCircle;
    }

    @Override
    public double area() {

        double areaOfCircle = Math.PI * Math.pow(radiusOfCircle, 2);
        return areaOfCircle;
    }

    @Override
    public void displayInfo() {
        String info = "Shape:" + this.getClass().getSimpleName()
                + System.lineSeparator() + "Radius: " + radiusOfCircle
                + System.lineSeparator() + "Area: " + String.format("%.2f", area())
                + System.lineSeparator() + "Perimeter: " + perimeter
                + System.lineSeparator();
        System.out.println(info);
    }


}
