package entity.player;

import items.Item;

import java.util.ArrayList;

public class Backpack {
    private final ArrayList<Item> items;


    public Backpack() {
        this.items = new ArrayList<>();
    }

    public void printBackpackContent () {

        if (items.isEmpty()) {
            System.out.println("Player's backpack is empty");

        } else {

            System.out.println("Backpack contains the following items: ");
            for (Item item : items) {
                System.out.format("\t%s named : %s\n",item.getClass().getSimpleName(), item.getName());
            }
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }


    public ArrayList<Item> getItems() {
        return items;
    }
}
