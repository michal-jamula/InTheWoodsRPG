package world.impl;

import entity.player.Player;
import items.Item;
import items.Weapon;
import world.Action;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Contains logic and functionality for the player to equip items
public class EquipAction implements Action {

    private String playerAction;
    private String itemName;

    public EquipAction(String playerAction, String itemName) {
        this.playerAction = playerAction;
        this.itemName = itemName;
    }
    @Override
    public void executeAction(Player player) {

        if (playerAction.equalsIgnoreCase("equip")) {
            Optional<Item> itemOptional = player.getBackpack().getItems().stream().filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findFirst();

            if (itemOptional.isEmpty()) {
                System.out.format("Item named %s is not found.\n", itemName);
            } else {
                Item item = itemOptional.get();
                if (item.getClass().getSimpleName().equalsIgnoreCase("Weapon")) {
                    player.equipWeapon((Weapon) item);
                    System.out.format("Player equips %s called %s\n", item.getClass().getSimpleName(), item.getName());
                    return;
                } else {
                    System.out.println("Player tried to equip an invalid item!");
                    return;
                }
            }
        } else if (playerAction.equalsIgnoreCase("unequip")) {

            if (player.getItems().getWeapon().getName().equalsIgnoreCase(itemName)) {
                player.unequipWeapon();
                System.out.println("Player unequips " + itemName);
            } else {
                System.out.println("Item named %s is not found.");
            }


        } else {
            System.out.println("Unknown equip action.");
        }




    }
}
