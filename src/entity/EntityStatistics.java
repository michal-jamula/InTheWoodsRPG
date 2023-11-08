package entity;

public class EntityStatistics {
    Integer armour;
    Integer strength;
    Integer vitality;


    @Override
    public String toString() {
        return String.format("\tArmour: %s\n\tStrength: %s\n\tVitality: %s", armour, strength, vitality);
    }

    public EntityStatistics(Integer armour, Integer strength, Integer vitality) {
        this.armour = armour;
        this.strength = strength;
        this.vitality = vitality;
    }

    public EntityStatistics() {
    }

    public EntityStatistics addStats(EntityStatistics es) {
        return new EntityStatistics(this.armour+= es.getArmour(), this.strength+= es.strength, this.vitality += es.getVitality());
    }

    public EntityStatistics takeStats(EntityStatistics es) {
        return new EntityStatistics(this.armour-= es.getArmour(), this.strength-= es.strength, this.vitality -= es.getVitality());
    }


    public Integer getArmour() {
        return armour;
    }

    public void setArmour(Integer armour) {
        this.armour = armour;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }
}
