package items;

import world.Location;

//This class acts as a container for an Item class which is placed on the map
public class FloorItem {

    protected Location location;
    private String name;

    private Item item;

    public FloorItem (Item item, Location location) {
        this.item = item;
        this.location = location;
        this.name = item.getName();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
