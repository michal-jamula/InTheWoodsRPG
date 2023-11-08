package main;

import entity.Enemy;
import entity.player.Player;
import entity.QuestGiver;
import items.FloorItem;
import items.Herb;
import items.Weapon;
import map.Map;
import world.Direction;
import world.impl.*;
import world.Location;
import world.interaction.MapInteraction;

import java.util.Arrays;
import java.util.Scanner;


//This class is in charge of the main game logic and functionality
public class Game {

    private final Player player;
    private final QuestGiver questGiverJohn = new QuestGiver(new Location(3,3), "John");

    public Game() {
        this.player = new Player(new Location(0, 0), "player1");

        Map.INSTANCE.registerItem(new FloorItem(new Weapon("Pike"), new Location(0,1)));
        Map.INSTANCE.registerItem(new FloorItem(new Weapon("Hammer"), new Location(4,4)));
        Map.INSTANCE.registerItem(new FloorItem(new Weapon("Mace"), new Location(1,3)));
        Map.INSTANCE.registerItem(new FloorItem(new Weapon("Spear"), new Location(5,5)));
        Map.INSTANCE.registerItem(new FloorItem(new Weapon("Sword"), new Location(2,4)));
        Map.INSTANCE.registerItem(new FloorItem(new Herb("Dreamleaf"), new Location(4,4)));
        Map.INSTANCE.registerItem(new FloorItem(new Herb("Bloodroot"), new Location(2,5)));
        Map.INSTANCE.registerItem(new FloorItem(new Herb("Starbloom"), new Location(0,4)));
        Map.INSTANCE.registerItem(new FloorItem(new Herb("Nightshade"), new Location(1,1)));
        Map.INSTANCE.registerEntity(new Enemy(new Location(1,1), "Spider"));
        Map.INSTANCE.registerEntity(new Enemy(new Location(1,4), "Worm"));
        Map.INSTANCE.registerEntity(new Enemy(new Location(4,5), "Tiger"));
        Map.INSTANCE.registerEntity(new Enemy(new Location(5,1), "Bear"));



        Map.INSTANCE.registerEntity(questGiverJohn);

        while (true) {
            this.getPlayersMove(this.askPlayerForAnOption());
        }
    }


    private void getPlayersMove(String playerOptions) {
        String playerAction = "";
        String playerOption;


        try {
            playerAction = playerOptions.split("\\s+")[0];
            playerOption = playerOptions.split("\\s+")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            playerOption = "NO_DIRECTION";
        }

        final String finalPlayerAction = playerAction;


        if (Arrays.stream(MapInteraction.class.getEnumConstants()).anyMatch(enumValue -> finalPlayerAction.equalsIgnoreCase(enumValue.name()))) {
            mapAction(playerAction, playerOption);
        } else if (playerAction.equalsIgnoreCase("fight")) {
            player.doAction(new FightAction(playerOption));
        } else if (playerAction.equalsIgnoreCase("talk")) {
            player.doAction(new DialogAction(playerOption));
        } else if (playerAction.equalsIgnoreCase("show")) {
            player.doAction(new ShowAction(playerOption));
        } else if (playerAction.equalsIgnoreCase("equip") || playerAction.equalsIgnoreCase("unequip")) {
            player.doAction(new EquipAction(playerAction, playerOption));
        } else if (playerAction.equalsIgnoreCase("exit")) {
            System.exit(1);
        }

        else {
                System.out.println("Can't do that action!");
            }
        }





    private void mapAction(String playerAction, String playerDirection) {
        Direction direction;
        var directionOptional = Direction.getDirection(playerDirection);

        direction = directionOptional.orElse(Direction.NO_DIRECTION);

        if (playerAction.equalsIgnoreCase(MapInteraction.MOVE.name())) {
            player.doAction(new MoveAction(direction));
        } else if (playerAction.equalsIgnoreCase(MapInteraction.LOOK.name())) {
            player.doAction(new LookAction((direction)));
        } else if (playerAction.equalsIgnoreCase(MapInteraction.TAKE.name())) {
            player.doAction(new TakeAction(playerDirection));
        } else if (playerAction.equalsIgnoreCase(MapInteraction.HELP.name())) {
            printMainGameHelp();
        }
    }


    private String askPlayerForAnOption() {
        Scanner scanner = new Scanner(System.in);
        String userDecision = scanner.nextLine();
        return userDecision.trim();
    }

    private void printMainGameHelp() {
        System.out.println("Available game commands:");
        System.out.println("\thelp");
        System.out.println("\tmove <direction> (north, south, east, west)");
        System.out.println("\tlook <direction> (north, south, east, west) or no direction");
        System.out.println("\tfight <enemy name>");
        System.out.println("\tshow <player info> (backpack, quests, stats, equipment)");
        System.out.println("\ttalk <name>");
        System.out.println("\texit");
    }

}
