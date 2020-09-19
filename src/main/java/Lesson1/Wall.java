package Lesson1;

public class Wall implements IObstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
