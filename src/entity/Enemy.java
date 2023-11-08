package entity;

import world.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity {

    private static final List<String> possibleEnemyNames = Arrays.asList("Troll","Deadling", "Germword", "Germtalon", "Cursecat", "Deadwing", "Grossman", "Soilbug");
    public Enemy(Location location, String name) {
        super(location, name);
    }


    public static String createRandomEnemyName() {
        Random random = new Random();
        return possibleEnemyNames.get(random.nextInt(possibleEnemyNames.size()));
    }
}
