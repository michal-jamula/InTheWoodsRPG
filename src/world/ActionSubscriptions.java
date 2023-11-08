package world;

import entity.player.Player;
import items.Item;
import world.subscriptions.TakeActionSubscription;

import java.util.ArrayList;
import java.util.List;

//Used to hold quest subscriptions, so they're updated when player picks up an item
public class ActionSubscriptions {

    private static final List<ActionSubscription> actionSubscriptionList = new ArrayList<>();

    public static void subscribe(ActionSubscription actionSubscription) {
        actionSubscriptionList.add(actionSubscription);
    }

    public static void unsubscribe (ActionSubscription actionSubscription) {
        actionSubscriptionList.remove(actionSubscription);
    }


    public static void push(Player player, Item item) {
        for (ActionSubscription as : actionSubscriptionList) {
            if (as instanceof TakeActionSubscription) {
                ((TakeActionSubscription) as).onTake(player, item);
            }
        }

    }

}
