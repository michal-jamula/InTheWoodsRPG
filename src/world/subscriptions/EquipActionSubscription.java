package world.subscriptions;

import entity.player.Player;
import items.Item;
import world.ActionSubscription;

@FunctionalInterface
public interface EquipActionSubscription extends ActionSubscription {

    void onEquip(Player player, Item item);
}
