package world;

import java.util.Objects;

//Used by various Entities to store their location on a map
public class Location {

    private final int xAxis;
    private final int yAxis;


    public Location(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }


    public int getX() {
        return xAxis;
    }

    public int getY() {
        return yAxis;
    }


    public final Location translate(int x, int y) {
        return new Location(xAxis + x, yAxis + y);
    }

    public Location shift(Direction direction) {
        return switch (direction) {
            case NORTH -> translate(0, 1);
            case EAST -> translate(1, 0);
            case SOUTH -> translate(0, -1);
            case WEST -> translate(-1, 0);
            case NO_DIRECTION -> translate(0, 0);
        };
    }


    @Override
    public String toString() {
        return String.format("Location{%s,%s}", xAxis, yAxis);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return xAxis == location.xAxis && yAxis == location.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, yAxis);
    }
}
