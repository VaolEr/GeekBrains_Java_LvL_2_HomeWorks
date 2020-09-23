package Lesson1;

import java.util.Objects;
import java.util.Random;

public class Robot implements IRun, IJump, IObstaclePassed{

    private String robotName;
    private int jumpHeight;
    private final int maxJumpHeight = 2;
    private int jumpLength;
    private final int maxJumpLength = 3;
    private int runDistance;
    private final int maxRunDistance = 1000;

    Random random = new Random();

    public Robot(String robotName) {
        this.robotName = robotName;
        this.jumpHeight = random.nextInt(maxJumpHeight);
        this.jumpLength = random.nextInt(maxJumpLength);
        this.runDistance = random.nextInt(maxRunDistance);
    }

    public String getRobotName() {
        return robotName;
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
            System.out.printf("Robot %s jumped a wall high %d.\n", this.robotName, wall.getHeight());
            return true;
        }
        else
        {
            System.out.printf("Robot %s cannot jump over a wall %d high. It can jump only %d high.\n", this.robotName, wall.getHeight(), this.jumpHeight);
            return false;
        }
    }

    @Override
    public boolean longJumpMethod(Pit pit) {
        if(pit.getLength() <= this.jumpLength) {
            System.out.printf("Robot %s jumped a pit length %d.\n", this.robotName, pit.getLength());
            return true;
        }
        else {
            System.out.printf("Robot %s cannot jump over a pit %d length. It can jump only %d length.\n", this.robotName, pit.getLength(), this.jumpLength);
            return false;
        }
    }

    @Override
    public boolean runMethod(RaceTrack track) {
        if(track.getLength() <= this.runDistance) {
            System.out.printf("Robot %s ran %d track length.\n", this.robotName, track.getLength());
            return true;
        }
        else{
            System.out.printf("Robot %s cannot run over a track %d length. It can run only %d length.\n", this.robotName, track.getLength(), this.runDistance);
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
        Robot robot = (Robot) o;
        return jumpHeight == robot.jumpHeight &&
                maxJumpHeight == robot.maxJumpHeight &&
                jumpLength == robot.jumpLength &&
                maxJumpLength == robot.maxJumpLength &&
                runDistance == robot.runDistance &&
                maxRunDistance == robot.maxRunDistance &&
                robotName.equals(robot.robotName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(robotName, jumpHeight, maxJumpHeight, jumpLength, maxJumpLength, runDistance, maxRunDistance);
    }
}
