package world;

import entity.player.Player;

@FunctionalInterface
public interface Action {

    void executeAction(Player player);
}
