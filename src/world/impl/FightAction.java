package world.impl;

import entity.Entity;
import entity.player.Player;
import map.Map;
import world.Action;

//Validates and does fight logic
public class FightAction implements Action {

    private final String enemyName;

    public FightAction(String enemyName) {
        this.enemyName = enemyName;
    }
    @Override
    public void executeAction(Player player) {

        for (Entity entity : Map.INSTANCE.getEntityClassesInLocation(player.getLocation()).keySet()) {
            if (entity.getName().equalsIgnoreCase(enemyName) && entity.getClass().getSimpleName().equals("Enemy")) {

                System.out.format("Player fights enemy named \"%s\" and kills it. Congratulations!%n", enemyName);
                Map.INSTANCE.removeEntity(entity);
                return;

            }
        }

            System.out.format("No enemy named %s in this location.\n", enemyName);

    }
}
