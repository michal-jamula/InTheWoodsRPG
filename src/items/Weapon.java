package items;

import entity.EntityStatistics;

public class Weapon extends Item{

    EntityStatistics weaponStats;
    public Weapon(String name) {
        super(name);
        this.weaponStats = new EntityStatistics(0, 5, 10);
    }

    public EntityStatistics getStats() {
        return weaponStats;
    }

    public void setStats(EntityStatistics weaponStats) {
        this.weaponStats = weaponStats;
    }
}
