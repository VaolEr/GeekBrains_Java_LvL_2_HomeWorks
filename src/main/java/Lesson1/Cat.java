package Lesson1;

import java.util.Objects;
import java.util.Random;

public class Cat implements IRun, IJump, IObstaclePassed{

    private String catName;
    private int jumpHeight;
    private final int maxJumpHeight = 10;
    private int jumpLength;
    private final int maxJumpLength = 10;
    private int runDistance;
    private final int maxRunDistance = 10;

    Random random = new Random();

    public Cat(String catName) {
        this.catName = catName;
        this.jumpHeight = random.nextInt(maxJumpHeight);
        this.jumpLength = random.nextInt(maxJumpLength);
        this.runDistance = random.nextInt(maxRunDistance);
    }

    public String getCatName() {
        return catName;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getJumpLength() {
        return jumpLength;
    }

    public int getRunDistance() {
        return runDistance;
    }

    @Override
    public boolean highJumpMethod(Wall wall) {
        if(wall.getHeight() <= this.jumpHeight) {
            System.out.printf("Cat %s jumped a wall high %d.\n", this.catName, wall.getHeight());
            return true;
        }
        else
        {
            System.out.printf("Cat %s cannot jump over a wall %d high. It can jump only %d high.\n", this.catName, wall.getHeight(), this.jumpHeight);
            return false;
        }
    }

    @Override
    public boolean longJumpMethod(Pit pit) {
        if(pit.getLength() <= this.jumpLength) {
            System.out.printf("Cat %s jumped a pit length %d.\n", this.catName, pit.getLength());
            return true;
        }
        else {
            System.out.printf("Cat %s cannot jump over a pit %d length. It can jump only %d length.\n", this.catName, pit.getLength(), this.jumpLength);
            return false;
        }
    }

    @Override
    public boolean runMethod(RaceTrack track) {
        if(track.getLength() <= this.runDistance) {
            System.out.printf("Cat %s ran %d track length.\n", this.catName, track.getLength());
            return true;
        }
        else{
            System.out.printf("Cat %s cannot run over a track %d length. It can run only %d length.\n", this.catName, track.getLength(), this.runDistance);
            return false;
        }
    }

    @Override
    public boolean obstaclePassed(IObstacle obstacle){
        boolean passed = false;
        if (obstacle instanceof Wall) {
            passed = highJumpMethod((Wall) obstacle);
        } else if (obstacle instanceof Pit) {
            passed = longJumpMethod((Pit) obstacle);
        } else if (obstacle instanceof RaceTrack) {
            passed = runMethod((RaceTrack) obstacle);
        }
        return passed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return jumpHeight == cat.jumpHeight &&
                maxJumpHeight == cat.maxJumpHeight &&
                jumpLength == cat.jumpLength &&
                maxJumpLength == cat.maxJumpLength &&
                runDistance == cat.runDistance &&
                maxRunDistance == cat.maxRunDistance &&
                catName.equals(cat.catName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catName, jumpHeight, maxJumpHeight, jumpLength, maxJumpLength, runDistance, maxRunDistance);
    }
}
