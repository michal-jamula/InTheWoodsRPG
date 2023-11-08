package world.impl;

import entity.player.Player;
import items.FloorItem;
import map.Map;
import world.Action;
import world.ActionSubscriptions;

//Used to validate and take items from a map location
public class TakeAction implements Action {

    private final String lootItemName;

    public TakeAction(String lootItem) {
        this.lootItemName = lootItem;
    }

    @Override
    public void executeAction(Player player) {

        var entities = Map.INSTANCE.getEntitiesInLocation(player.getLocation());
        var items = Map.INSTANCE.getItemsInLocation(player.getLocation());

        if (!entities.isEmpty()) {
            System.out.println("Enemy found at this location. Defeat the enemy to loot items!");
            return;
        }


        for (FloorItem item : items) {
            if (item.getName().equalsIgnoreCase(lootItemName)) {
                player.getBackpack().addItem(item.getItem());
                System.out.format("Player takes %s named %s from %s\n", item.getItem().getClass().getSimpleName(), item.getName(), item.getLocation());
                Map.INSTANCE.removeItem(item);
                ActionSubscriptions.push(player, item.getItem());
                return;
            }
        }
        System.out.format("No item named %s found at this location\n", lootItemName);

    }
}
