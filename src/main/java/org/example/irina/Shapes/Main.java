package org.example.irina.Shapes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList shapesList = new ArrayList<>();

        Circle circle1 = new Circle("Circle", 5);
        Rectangle rectangle1 = new Rectangle("Rectangle", 8, 4);
        Triangle triangle1 = new Triangle("Triangle", 9, 12);

        shapesList.add(circle1);
        shapesList.add(rectangle1);
        shapesList.add(triangle1);

        System.out.println();
        shapesList.forEach((n) -> System.out.println(n));
        System.out.println();

        shapesList.forEach(n -> System.out.println(n.getClass().getSimpleName()));
        System.out.println();

        circle1.displayInfo();
        rectangle1.displayInfo();
        triangle1.displayInfo();
    }
}
