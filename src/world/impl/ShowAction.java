package world.impl;

import entity.player.Player;
import world.Action;
import world.interaction.ShowPlayerInfoInteraction;

// Used for the "Show" player action
public class ShowAction implements Action {

    private final String playerOption;

    public ShowAction (String playerOption) {
        this.playerOption = playerOption;
    }


    @Override
    public void executeAction(Player player) {

        if (playerOption.equalsIgnoreCase(ShowPlayerInfoInteraction.BACKPACK.name())) {
            player.printBackpack();
        } else if (playerOption.equalsIgnoreCase(ShowPlayerInfoInteraction.QUESTS.name())) {
            player.printQuests();
        } else if (playerOption.equalsIgnoreCase(ShowPlayerInfoInteraction.EQUIPMENT.name())) {
            player.printEquipment();
        } else if (playerOption.equalsIgnoreCase(ShowPlayerInfoInteraction.STATS.name())) {
            player.printStats();
        } else {
            System.out.println("Invalid show action");
        }

    }
}
