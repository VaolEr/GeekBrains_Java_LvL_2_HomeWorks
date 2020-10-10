package Lesson5;

public class Main {
    public static void main(String[] args) {

        ThreadTestClass obj = new ThreadTestClass();

        System.out.println("\nWithout threads:");
        System.out.println("\nTotal Time Without Threads: " + obj.calculateSizeValuesWithoutThreads());

        System.out.println("\n//------------------------------------------//\n");

        try {
            System.out.println("With threads:");
            System.out.println("\nTotal Time With Threads: " + obj.calculateSizeValuesWith2Threads());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
