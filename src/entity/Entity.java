package entity;

import world.Location;

public abstract class Entity {
    protected Location currentLocation;
    private String name;
    protected MovementController movementController;


    public Entity(Location location, String name) {
        this.currentLocation = location;
        this.name = name;
        this.movementController = new MovementController(this);
    }


    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public MovementController getMovementController() {
        return movementController;
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
