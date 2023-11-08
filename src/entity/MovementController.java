package entity;

import map.Map;
import world.Direction;
import world.Location;

//Used to move Entities and validate their steps
public class MovementController {

    public final Entity entity;


    public MovementController(Entity entity) {
        this.entity = entity;
    }

    public void step(Direction direction) {
        final var currentLocation = entity.getLocation();
        var newLocation = currentLocation.shift(direction);

        if (validateStep(newLocation)) {
            entity.setCurrentLocation(newLocation);
            System.out.println("Player's new location: " + newLocation);
        } else {
            System.out.println("Player tried to move to an invalid location");
            entity.setCurrentLocation(currentLocation);
        }
    }


    public Entity getEntity() {
        return entity;
    }


    private boolean validateStep(Location location) {
        return location.getX() <= Map.INSTANCE.getMaxX() && location.getY() <= Map.INSTANCE.getMaxY()
                && location.getX() >= Map.INSTANCE.getMinX() && location.getY() >= Map.INSTANCE.getMinY();
    }
}
