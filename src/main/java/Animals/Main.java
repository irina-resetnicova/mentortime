package Animals;

public class Main {
    public static void main(String[] args) {

        Cat cat = new Cat();
        Dog dog = new Dog();

        Animal animal1 = cat;
        System.out.println(cat instanceof Animal);
        System.out.println(cat instanceof Cat);


        Animal animal2 = dog;
        System.out.println(dog instanceof Animal);
        System.out.println(dog instanceof Dog);

        animal1.makeSound();
        animal2.makeSound();


    }
}
