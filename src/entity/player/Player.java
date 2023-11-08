package entity.player;

import entity.Entity;
import entity.EntityStatistics;
import items.Weapon;
import quest.Quest;
import world.Action;
import world.Location;
import world.subscriptions.EquipActionSubscription;
import world.subscriptions.TakeActionSubscription;

import java.util.ArrayList;



public class Player extends Entity {

    private final ArrayList<Quest> activeQuests;
    private final Backpack backpack;
    private final EquippedItems items;
    private final EntityStatistics playerStatistics;




    public Player(Location location, String name) {
        super(location, name);
        this.backpack = new Backpack();
        this.activeQuests = new ArrayList<>();
        this.items = new EquippedItems();
        this.playerStatistics = new EntityStatistics(20, 2, 2);
    }

    public void doAction(Action action) {
        action.executeAction(this);
    }

    public void finishQuest (Quest quest) {
        this.deleteQuest(quest);
    }

    public void addActiveQuest(Quest quest) {
        this.activeQuests.add(quest);
    }

    public void deleteQuest(Quest quest) {
        this.activeQuests.remove(quest);
    }

    public void printEquipment() {
        System.out.println("Player equipment: ");
        this.items.printEquippedItems();
    }

    public void equipWeapon(Weapon weapon) {
        if (items.getWeapon() == null) {
            items.equipWeapon(weapon);
            playerStatistics.addStats(weapon.getStats());
            this.backpack.removeItem(weapon);
        } else {
            this.unequipWeapon();
            this.equipWeapon(weapon);
        }
    }

    public void unequipWeapon() {
        Weapon weapon = items.getWeapon();
        playerStatistics.takeStats(weapon.getStats());
        backpack.addItem(weapon);
        items.setWeapon(null);
    }

    public void printQuests() {
        if (activeQuests.isEmpty()) {
            System.out.println("Player has no active quests.");
        } else {
            System.out.println("Player has the following quests: ");
            for (Quest quest : activeQuests) {
                System.out.format("\t%s : %s. This quest is %s\n", quest.getQuestName(), quest.getQuestDescription(), quest.isComplete() ? "complete" : "incomplete");
            }
        }
    }

    public void printBackpack() {
        backpack.printBackpackContent();
    }

    public void printStats () {
        System.out.println("Player statistics: ");
        System.out.println(playerStatistics);
    }

    public Integer getX() {
        return currentLocation.getX();
    }

    public Integer getY() {
        return currentLocation.getY();
    }

    public ArrayList<Quest> getActiveQuests() {
        return activeQuests;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public EquippedItems getItems() {
        return items;
    }
}
