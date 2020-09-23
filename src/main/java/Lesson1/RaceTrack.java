package Lesson1;

public class RaceTrack implements IObstacle {

    private int length;

    public RaceTrack(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
