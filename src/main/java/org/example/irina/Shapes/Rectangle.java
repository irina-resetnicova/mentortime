package org.example.irina.Shapes;

public class Rectangle extends Shape {
    double widthOfRectangle;
    double heightOfRectangle;

    public Rectangle(String name, double widthOfRectangle, double heightOfRectangle) {
        super(name);
        this.widthOfRectangle = widthOfRectangle;
        this.heightOfRectangle = heightOfRectangle;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name='" + name + '\'' +
                ", widthOfRectangle=" + widthOfRectangle +
                ", heightOfRectangle=" + heightOfRectangle +
                '}';
    }

    @Override
    public double perimeter() {
        double perimeterOfRectangle = (widthOfRectangle + heightOfRectangle) * 2;
        return perimeterOfRectangle;
    }

    @Override
    public double area() {
        double areaOfRectangle = widthOfRectangle * heightOfRectangle;
        return areaOfRectangle;
    }

    @Override
    public void displayInfo() {
        String info = "Shape:" + this.getClass().getSimpleName()
                + System.lineSeparator() + "Width: " + widthOfRectangle
                + System.lineSeparator() + "Height: " + heightOfRectangle
                + System.lineSeparator() + "Area: " + area()
                + System.lineSeparator() + "Perimeter: " + perimeter
                + System.lineSeparator();
        System.out.println(info);
    }
}
