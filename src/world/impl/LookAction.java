package world.impl;

import entity.Entity;
import entity.player.Player;
import items.FloorItem;
import world.Action;
import world.Direction;

import java.util.Map;
import java.util.stream.Collectors;

//Used by the player to either look closely at current location, or look at bordering locations
public class LookAction implements Action {

    private final Direction direction;
    public LookAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void executeAction(Player player) {

        Map<Entity, Long> entityList = map.Map.INSTANCE.getEntityClassesInLocation(player.getLocation());
        Map<FloorItem, Long> itemList = map.Map.INSTANCE.getItemClassesInLocation(player.getLocation());

        if (direction == Direction.NO_DIRECTION) {
            if (!entityList.isEmpty() || !itemList.isEmpty()) {
                System.out.println("Player looks closer at: ");
                for (Entity entity : entityList.keySet()) {
                    System.out.format("\t%s named: %s\n", entity.getClass().getSimpleName(), entity.getName());
                }

                for (FloorItem item : itemList.keySet()) {
                    System.out.format("\t%s named: %s\n", item.getItem().getClass().getSimpleName(), item.getName());
                }
                return;
            }

            System.out.println("Player takes a closer look and doesn't find anything.");

        } else {
            entityList = map.Map.INSTANCE.getEntityClassesInLocation(player.getLocation().shift(direction));
            itemList = map.Map.INSTANCE.getItemClassesInLocation(player.getLocation().shift(direction));
            if (map.Map.INSTANCE.locationIsEmpty(player.getLocation()))
            System.out.println("Player looks at the following: ");
            entityList.entrySet().stream()
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().getClass().getSimpleName(),
                            Collectors.summingLong(Map.Entry::getValue)
                    ))
                    .forEach((name, count) -> System.out.println("\t" + name + ": " + count));

            itemList.entrySet().stream()
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().getItem().getClass().getSimpleName(),
                            Collectors.summingLong(Map.Entry::getValue)
                    ))
                    .forEach((name, count) -> System.out.println("\t" + name + ": " + count));


            if (entityList.isEmpty() && itemList.isEmpty()) {
                System.out.println("Player looks at nothing interesting");
            }
        }

    }
}
