package quest;

import entity.Enemy;
import map.Map;
import quest.questBuilder.SimpleHerbQuest;
import quest.questBuilder.SimpleWeaponQuest;

//This class is used to build quests
public class QuestController {

    public QuestController () {}


    public Quest buildQuest (QuestType questType) {
        return switch (questType) {
            case HERB_QUEST -> createNewHerbQuest();
            case ITEM_QUEST -> createNewItemQuest();
        };
    }

    private Quest createNewHerbQuest() {
        return new SimpleHerbQuest();
    }

    private Quest createNewItemQuest() {
        SimpleWeaponQuest quest = new SimpleWeaponQuest();
        Map.INSTANCE.registerEntity(new Enemy(quest.getLocation(), Enemy.createRandomEnemyName()));
        return quest;
    }



}
