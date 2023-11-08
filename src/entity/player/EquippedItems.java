package entity.player;

import items.Weapon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//Used by the player to store equipped items
public class EquippedItems {

    private Weapon weapon;

    public EquippedItems(){}

    public void printEquippedItems() {
        System.out.format("\tWeapon: %s\n", this.weapon != null ? weapon.getName() : "empty");
    }

    public void equipWeapon (Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
