package world.impl;

import entity.Entity;
import entity.player.Player;
import entity.QuestGiver;
import map.Map;
import world.Action;

//Used to validate that a QuestGiver is in such location
public class DialogAction implements Action {

    private final String dialogCharacter;

    public DialogAction(String dialogCharacter) {
        this.dialogCharacter = dialogCharacter;
    }

    @Override
    public void executeAction(Player player) {

        for (Entity entity : Map.INSTANCE.getEntitiesInLocation(player.getLocation())) {
            if (entity.getClass().getSimpleName().equals("QuestGiver") && dialogCharacter.equalsIgnoreCase(entity.getName())) {
                QuestGiver questGiver = (QuestGiver) entity;
                questGiver.interactWithCharacter(player);
                return;
            }
        }

        System.out.println("No one to talk to at this location.");

    }

}
