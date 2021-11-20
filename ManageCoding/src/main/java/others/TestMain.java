package others;

class Animal{
    public Animal(){
        System.out.println(11);
    }

    public void move(){
        System.out.println(1);
    }
}

class Dog extends Animal{
    public void move(){
        System.out.println(4);
    }
    public void bark(){
        System.out.println(3);
    }
    public Dog(int a){
        System.out.println(22);
    }
}

public class TestMain {
    public static void main(String[] args) {
        Animal a = new Animal();
        Animal b = new Dog(1);
        /*a.move();
        b.move();*/
    }
}