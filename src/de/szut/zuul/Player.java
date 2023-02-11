package de.szut.zuul;

import java.util.LinkedList;
import java.util.Formatter;

public class Player {
    Room currentRoom;

    double loadCapacity = 10.0;

    double currentLoad = 0.0;

    LinkedList<Item> inventory;


    public Room getCurrentRoom() { return currentRoom; }

    public void goToRoom(Room room) { this.currentRoom = room; }

    public boolean tryTakeItem(Item item) {
        if (item.getWeight() + currentLoad > loadCapacity) return false;
        addItemToInventory(item);
        return true;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        currentLoad += item.getWeight();
    }

    public Item dropItem(String name) {
        try {
            Item i = inventory.get(name);
            inventory.remove(name);
            return i;
        } catch (Exception e) {
            return null;
        }
    }

    public String showStatus() {
        StringBuilder str = new StringBuilder();

        str.append("Status of the player\n")
                .append(new Formatter().format("loadCapacity: %f kg\n", loadCapacity).toString());
        for (Item i: inventory) {
            str.append("taken items: ")
                    .append(i.getName())
                    .append(" ")
                    .append(i.getWeight())
                    .append("kg\n");
        }

        str.append(new Formatter().format("absorbed weight: %f kg\n", currentLoad).toString());

        return str.toString();
    }
}
