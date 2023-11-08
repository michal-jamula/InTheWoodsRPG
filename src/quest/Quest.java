package quest;

import entity.QuestGiver;
import items.Item;
import map.Map;
import world.ActionSubscription;
import world.Location;

import java.util.Random;

public abstract class Quest {
    protected String questName;
    protected Location location;
    protected boolean isComplete;
    protected String questDescription;
    protected Item questItem;
    protected ActionSubscription subscription;

    public Quest(){}
    public Quest (String questName, Location questLocation, String questDescription) {
        this.questName = questName;
        this.location = questLocation;
        this.isComplete = false;
        this.questDescription = questDescription;
    }

    public Quest (String questName, Item questItem, String questDescription) {
        this.questName = questName;
        this.questItem = questItem;
        this.questDescription = questDescription;
    }

    public abstract void onComplete ();



    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public Item getQuestItem() {
        return questItem;
    }

    protected String generateQuestDescription(String questName, Location questLocation){
        return String.format("John asked you to bring him %s. He believes it's guarded by an enemy. It's located at %s", questName, questLocation);
    }

    protected Location generateRandomValidQuestLocation() {
        Random rand = new Random();
        Location location = new Location(rand.nextInt(5), rand.nextInt(5));
        if (Map.INSTANCE.getEntitiesInLocation(location).stream().anyMatch(questGiver -> questGiver instanceof QuestGiver)){
            return this.generateRandomValidQuestLocation();
        } else {
            return location;
        }
    }
}
