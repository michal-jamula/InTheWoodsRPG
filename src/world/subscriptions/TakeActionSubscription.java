package world.subscriptions;

import entity.player.Player;
import items.Item;
import world.ActionSubscription;

@FunctionalInterface
public interface TakeActionSubscription extends ActionSubscription {

    void onTake(Player player, Item item);

}
