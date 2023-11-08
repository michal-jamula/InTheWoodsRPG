package world;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//possible directions when player moves on the map
public enum Direction {

    NORTH,

    SOUTH,

    EAST,

    WEST,

    NO_DIRECTION;


    private static final Map<String, Direction> VALUES = new HashMap<>();

    static {
        for (var value : values()) {
            VALUES.put(value.toString().toLowerCase(), value);
        }
    }

    public static Optional<Direction> getDirection(String option) {
        option = option.toLowerCase();

        if (option.isEmpty()) {
            return Optional.ofNullable(VALUES.get("NO_DIRECTION"));
        }

        return Optional.ofNullable(VALUES.get(option));
    }

}
