package Lesson1;

import java.util.Random;

//Task
//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//4.* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий, то дальше по списку препятствий он не идет.

public class Stadium {

    static final int NUMBER_OF_PLAYERS = 15;
    static final int NUMBER_OF_OBSTACLES = 5;

    static IObstaclePassed[] runPlayers = new IObstaclePassed[NUMBER_OF_PLAYERS]; //array of Elements with IRun interface
    static IObstacle[] obstacles = new IObstacle[NUMBER_OF_OBSTACLES]; // array of different obstacles
    static Random  random = new Random();

    public static void main(String[] args) {

        getArrayOfPlayers();
        getArrayOfObstacles();

        testingPlayersForStrength();

    }


    static void getArrayOfPlayers(){
        for(int i = 0; i < runPlayers.length; i++){
            int value = random.nextInt(3); // Rand values from 0 till 2
            //System.out.println(i +" "+ value);
            switch (value) {
                case 0 -> {
                    String catName = "Cat_" + i;
                    runPlayers[i] = new Cat(catName);
                    //System.out.println(runPlayers[i].getClass());
                    //break;
                }
                case 1 -> {
                    String humanName = "Human_" + i;
                    runPlayers[i] = new Human(humanName);
                    //break;
                }
                case 2 -> {
                    String robotName = "Robot_" + i;
                    runPlayers[i] = new Robot(robotName);
                    //break;
                }
                default -> {
                    String humanNamed = "Humand_" + i;
                    runPlayers[i] = new Human(humanNamed);
                    //break;
                }
            }
        }
    }

    static void getArrayOfObstacles(){
        for(int i = 0; i < obstacles.length; i++)
        {
            int value = random.nextInt(3); // Rand values from 0 till 2
            //System.out.println(i +" "+ value);
            switch (value) {
                case 0 -> {
                    obstacles[i] = new Wall((1 + random.nextInt(10)));
                    //System.out.println(obstacles[i].getClass());
                    //break;
                }
                case 1 -> {
                    obstacles[i] = new Pit((1 + random.nextInt(10)));
                    //break;
                }
                case 2 -> {
                    obstacles[i] = new RaceTrack((1 + random.nextInt(10)));
                    //break;
                }
                default -> {
                    obstacles[i] = new RaceTrack((1 + random.nextInt(5)));
                    //break;
                }
            }
        }
    }

    static void testingPlayersForStrength(){

        boolean result; //variable for check the object passed the obstacle

        for (IObstaclePassed runPlayer : runPlayers) {

            System.out.println("\n**---------------------------------------**\n"); //string separator

            for (int i = 0; i < obstacles.length; i++) {

                result = runPlayer.obstaclePassed(obstacles[i]);

                if (!result) {
                    System.out.println("Count of obstacles passed " + i);
                    break;
                }

                if (i == obstacles.length - 1) {
                    System.out.println("Passed all " + NUMBER_OF_OBSTACLES +" obstacles!");
                }
            }
        }
    }
}
