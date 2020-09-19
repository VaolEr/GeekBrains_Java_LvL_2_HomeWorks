package Lesson1;

import java.util.Objects;
import java.util.Random;

public class Human implements IRun, IJump, IObstaclePassed {

    private String humanName;
    private int jumpHeight;
    private final int maxJumpHeight = 15;
    private int jumpLength;
    private final int maxJumpLength = 5;
    private int runDistance;
    private final int maxRunDistance = 100;

    Random random = new Random();

    public Human(String humanName) {
        this.humanName = humanName;
        this.jumpHeight = random.nextInt(maxJumpHeight);
        this.jumpLength = random.nextInt(maxJumpLength);
        this.runDistance = random.nextInt(maxRunDistance);
    }

    public String getHumanName() {
        return humanName;
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
            System.out.printf("Human %s jumped a wall high %d.\n", this.humanName, wall.getHeight());
            return true;
        }
        else
        {
            System.out.printf("Human %s cannot jump over a wall %d high. He can jump only %d high.\n", this.humanName, wall.getHeight(), this.jumpHeight);
            return false;
        }
    }

    @Override
    public boolean longJumpMethod(Pit pit) {
        if(pit.getLength() <= this.jumpLength) {
            System.out.printf("Human %s jumped a pit length %d.\n", this.humanName, pit.getLength());
            return true;
        }
        else {
            System.out.printf("Human %s cannot jump over a pit %d length. He can jump only %d length.\n", this.humanName, pit.getLength(), this.jumpLength);
            return false;
        }
    }

    @Override
    public boolean runMethod(RaceTrack track) {
        if(track.getLength() <= this.runDistance) {
            System.out.printf("Human %s ran %d track length.\n", this.humanName, track.getLength());
            return true;
        }
        else{
            System.out.printf("Human %s cannot run over a track %d length. He can run only %d length.\n", this.humanName, track.getLength(), this.runDistance);
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
        Human human = (Human) o;
        return jumpHeight == human.jumpHeight &&
                maxJumpHeight == human.maxJumpHeight &&
                jumpLength == human.jumpLength &&
                maxJumpLength == human.maxJumpLength &&
                runDistance == human.runDistance &&
                maxRunDistance == human.maxRunDistance &&
                humanName.equals(human.humanName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanName, jumpHeight, maxJumpHeight, jumpLength, maxJumpLength, runDistance, maxRunDistance);
    }
}
