package org.example.irina.Shapes;

public class Shape {

    String name;
    double perimeter;
    double area;

    public Shape(String name) {
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("The name of the shape is: " + name);
    }

    public double perimeter() {
        return perimeter;
    }

    public double area() {
        return area;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                ", perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }
}

