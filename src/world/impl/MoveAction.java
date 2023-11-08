package world.impl;

import entity.player.Player;
import world.Action;
import world.Direction;

//Used to move the player in a direction
public class MoveAction implements Action {

    private final Direction direction;
    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void executeAction(Player player) {
        player.getMovementController().step(direction);
    }
}
