package Lesson1;

public class Pit implements IObstacle {
    private int length;

    public Pit(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
