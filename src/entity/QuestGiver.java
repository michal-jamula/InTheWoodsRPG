package entity;

import entity.player.Player;
import quest.Quest;
import quest.QuestController;
import quest.QuestType;
import world.Location;
import world.interaction.CharacterInteraction;
import world.interaction.QuestGiverInteraction;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//QuestGiver, currently there's only 1. Would have to be rewritten to support more quest givers
public class QuestGiver extends Entity implements CharacterInteraction {

    private final QuestController questController;

    public QuestGiver(Location location, String name) {
        super(location, name);
        this.movementController = null;
        this.questController = new QuestController();
    }


    @Override
    public void interactWithCharacter(Player player) {
        while (true) {
            System.out.format("Hello, my name is %s.\nDo you need anything?\n", this.getName());
            String playerOption = askPlayerForAnOption();

            switch (playerOption) {
                case "yes" :
                    if (player.getActiveQuests().isEmpty()) {
                        System.out.println("You don't have any active quests.");
                        System.out.println("Would you like the herb quest or the item quest?");
                        playerOption = askPlayerForAnOption();

                        if (playerOption.equalsIgnoreCase(QuestGiverInteraction.HELP.name())) {
                            helpMessage();
                        } else if (playerOption.equalsIgnoreCase(QuestGiverInteraction.HERB.name())) {
                            Quest quest = questController.buildQuest(QuestType.HERB_QUEST);
                            player.addActiveQuest(quest);
                            System.out.println(acceptedHerbQuestMessage(quest));
                            System.out.println("You have much to do. Go now.");
                            return;
                        } else if (playerOption.equalsIgnoreCase(QuestGiverInteraction.ITEM.name())) {
                            Quest quest = questController.buildQuest(QuestType.ITEM_QUEST);
                            player.addActiveQuest(quest);
                            System.out.println(acceptedItemQuestMessage(quest));
                            System.out.println("You have much to do. Go now.");
                            return;
                        } else if (playerOption.equalsIgnoreCase(QuestGiverInteraction.EXIT.name())) {
                            return;
                        }else {
                            System.out.println("Unexpected interaction with Quest Giver...");
                        }
                    }  else {

                        if (player.getActiveQuests().stream().anyMatch(Quest::isComplete)) {
                            System.out.println("You completed a quest.");
                            System.out.println("List of completed quests: ");
                            for (Quest quest : player.getActiveQuests()) {
                                if (quest.isComplete()) {
                                    System.out.format("\t%s : %s\n", quest.getQuestName(), quest.getQuestDescription());
                                }
                            }
                            System.out.println("Would you like to hand in your completed quests?");
                            playerOption = askPlayerForAnOption();
                        } else if (player.getActiveQuests().size() ==1 ) {
                            System.out.println("You have only 1 active quest, would you like another one? ");
                            playerOption = askPlayerForAnOption();

                            switch (playerOption.toLowerCase()) {
                                case "yes" :
                                    Quest quest;
                                    if (player.getActiveQuests().get(0).getClass().getSimpleName().equals("SimpleHerbQuest")) {
                                        quest = questController.buildQuest(QuestType.ITEM_QUEST);
                                        player.addActiveQuest(quest);
                                        System.out.println(acceptedItemQuestMessage(quest));
                                    } else {
                                        quest = questController.buildQuest(QuestType.HERB_QUEST);
                                        player.addActiveQuest(quest);
                                        System.out.println(acceptedHerbQuestMessage(quest));
                                    }
                                    System.out.println("You have much to do. Go now.");
                                    return;
                                case "help" : this.helpMessage(); return;
                                case "no", "exit" : return;
                            }

                        } else {
                            System.out.format("You have %s active quests, none of them are completed. Go finish these quests for me.\n", player.getActiveQuests().size());
                            return;
                        }


                        switch (playerOption.toLowerCase()) {
                            case "yes" :
                                for (int i = 0; i < player.getActiveQuests().size(); i++) {

                                    Quest quest = player.getActiveQuests().get(i);
                                    if (quest.isComplete() && player.getBackpack().getItems().contains(quest.getQuestItem())) {

                                        player.getBackpack().removeItem(quest.getQuestItem());
                                        player.finishQuest(quest);
                                        System.out.format("Thank you for bringing me the %s\n", quest.getQuestItem().getName());
                                        i = i-1;
                                    } else {
                                        System.out.format("I see you're wearing the %s, unequip it first to return the quest.\n", quest.getQuestItem().getName());
                                        return;
                                    }
                                }

                                System.out.println("There's always work that needs to get done. Come back to me for more quests.");
                                return;

                            case "no", "exit" :
                                System.out.println("Okay then. Come back to me if you need anything else.");
                                return;
                            case "help" :
                                this.helpMessage();
                        }

                    }
                case "no" :
                    System.out.println("Ok, talk to me if you need anything.");
                    return;

                case "help" :
                    this.helpMessage();
                    return;
            }









        }



    }

        private void helpMessage() {
        System.out.println("Available commands while speaking to the quest giver : ");
        System.out.println("\tyes");
        System.out.println("\tno");
        System.out.println("\therb");
        System.out.println("\titem");
        System.out.println("\thelp");
        System.out.println("\texit");
    }

    private String acceptedHerbQuestMessage(Quest quest) {
        List<String> herbDialog = Arrays.asList("There's this fascinating plant called",
                "I need you to fetch me a",
                "Can you get something for me? I need you to find a",
                "I'm trying to study the effects of"
        );
        Random random = new Random();

        return String.format("%s %s. I believe it's located at %s", herbDialog.get(random.nextInt(herbDialog.size())), quest.getQuestItem().getName(), quest.getLocation());

    }

    private String acceptedItemQuestMessage(Quest quest) {
        List<String> itemDialog = Arrays.asList("There's this fascinating item called",
                "Can you get something for me? I need you to find a",
                "I'm trying to study the effects of"
        );
        Random random = new Random();

        return String.format("%s %s. Be careful though, the item is guarded by an enemy. I believe you can find it at %s",
                itemDialog.get(random.nextInt(itemDialog.size())), quest.getQuestItem().getName(), quest.getLocation());

    }




    private String askPlayerForAnOption() {
        Scanner scanner = new Scanner(System.in);
        String userDecision = scanner.nextLine();
        return userDecision.trim();
    }
}
