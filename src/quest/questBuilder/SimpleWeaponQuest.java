package quest.questBuilder;

import items.Weapon;
import items.FloorItem;
import map.Map;
import quest.Quest;
import world.ActionSubscriptions;
import world.Location;
import world.subscriptions.TakeActionSubscription;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//This class is used to represent a simple weapon quest.
public class SimpleWeaponQuest extends Quest{

    private final List<String> listOfItems = Arrays.asList("Sword", "Mace", "Pike", "Hammer", "Spear");


    public SimpleWeaponQuest() {

        Weapon questItem = new Weapon(this.generateRandomItemName());
        FloorItem floorWeapon = new FloorItem(questItem, this.generateRandomValidQuestLocation());


        Map.INSTANCE.registerItem(floorWeapon);


        this.questName = questItem.getName();
        this.questItem = questItem;
        this.location = floorWeapon.getLocation();
        this.questDescription = generateQuestDescription(questName, location);
        this.subscription = (TakeActionSubscription) (player, floorItem) -> {
//            if (floorItem.getName().equalsIgnoreCase(questItem.getName())) {
//                this.isComplete = true;
//            }
            this.isComplete = player.getBackpack().getItems().contains(questItem);

        };
        ActionSubscriptions.subscribe(subscription);
    }

    @Override
    public void onComplete() {
        ActionSubscriptions.unsubscribe(subscription);
    }

    private String generateRandomItemName() {
        Random random = new Random();
        return listOfItems.get(random.nextInt(listOfItems.size()));
    }

}
