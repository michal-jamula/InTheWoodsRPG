package quest.questBuilder;

import items.FloorItem;
import items.Herb;
import map.Map;
import quest.Quest;
import world.ActionSubscriptions;
import world.Location;
import world.subscriptions.TakeActionSubscription;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//This class is used to represent a simple herb quest.
public class SimpleHerbQuest extends Quest {

    private static final List<String> listOfHerbs = Arrays.asList("Nightshade", "Bloodroot", "Firethorn", "Starbloom", "Graveflower", "Dreamleaf");


    public SimpleHerbQuest() {


        Herb questItem = new Herb(this.generateRandomName());
        FloorItem floorHerb = new FloorItem(questItem, this.generateRandomValidQuestLocation());


        Map.INSTANCE.registerItem(floorHerb);


       this.questName = questItem.getName();
       this.questItem = questItem;
       this.location = floorHerb.getLocation();
       this.questDescription = generateQuestDescription(questName, location);
       this.subscription = (TakeActionSubscription) (player, floorItem) -> {
            if (floorItem.getName().equalsIgnoreCase(questItem.getName())) {
                this.isComplete = true;
            }
        };
        ActionSubscriptions.subscribe(subscription);

    }

    @Override
    public void onComplete() {
        ActionSubscriptions.unsubscribe(subscription);
    }

    private String generateRandomName() {
        Random random = new Random();
        return listOfHerbs.get(random.nextInt(listOfHerbs.size()));
    }
}
