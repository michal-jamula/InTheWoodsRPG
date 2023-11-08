package map;

import entity.Entity;
import items.FloorItem;
import world.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//Singleton, it's used to store items and entities on the map
public class Map {

    public static final Map INSTANCE = new Map();
    private final List<Entity> entities = new ArrayList<>();
    private final List<FloorItem> items = new ArrayList<>();
    private final int maxX = 5;
    private final int maxY = 5;
    private final int minX = 0;
    private final int minY = 0;

    public List<Entity> getEntities() {
        return entities;
    }

    public void registerEntity(Entity entity) {
        entities.add(entity);
    }
    public void registerItem(FloorItem item) {items.add(item);}


    public List<Entity> getEntitiesInLocation(Location location) {
        return this.getEntities().stream().filter(entity -> entity.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    public List<FloorItem> getItemsInLocation (Location location) {
        return this.getItems().stream().filter(item -> item.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    public java.util.Map<Entity, Long> getEntityClassesInLocation(Location location) {

         return getEntities().stream()
                 .filter(e -> e.getLocation().equals(location))
                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public java.util.Map<FloorItem, Long> getItemClassesInLocation(Location location) {

        return getItems().stream()
                .filter(e -> e.getLocation().equals(location))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public void removeEntity(Entity entityToRemove) {
        entities.remove(entityToRemove);
    }
    public void removeItem (FloorItem floorItem) { items.remove(floorItem);}

    public boolean locationIsEmpty (Location location) {
        return getItemsInLocation(location).isEmpty() && getEntitiesInLocation(location).isEmpty();
    }

    private Map() {}

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public List<FloorItem> getItems() {
        return items;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }
}
