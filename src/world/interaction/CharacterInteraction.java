package world.interaction;

import entity.player.Player;

@FunctionalInterface
public interface CharacterInteraction {

    void interactWithCharacter (Player player);

}
