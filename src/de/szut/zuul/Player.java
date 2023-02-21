package de.szut.zuul;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Player {
    Room currentRoom;
    double loadCapacity;
    double currentLoad;
    LinkedList<Item> inventory;

    public Player() {
        this.loadCapacity = 10.0;
        this.currentLoad = 0.0;
        this.inventory = new LinkedList<>();
    }

    private double calculateWeight(double weight) {
        return currentLoad + weight;
    }

    private boolean isTakePossible(Item item) throws ItemTooHeavyException{
        return calculateWeight(item.getWeight()) <= loadCapacity;
    }

    public void takeItem(Item item) throws ItemTooHeavyException{
        if (currentLoad + item.getWeight() > loadCapacity) throw new ItemTooHeavyException();
        inventory.add(item);
        currentLoad += item.getWeight();
    }

    public Item dropItem(String itemName) throws ItemNotFoundException{
        int index = checkIfInInventory(itemName);
        if (!dropPossible(index)) throw new ItemNotFoundException();

        Item retItem = inventory.get(index);
        currentLoad -= retItem.getWeight();
        inventory.remove(index);
        return retItem;
    }
    private int checkIfInInventory(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (!Objects.equals(inventory.get(i).getName(), itemName)) continue;
            return i;
        }
        return -1;
    }
    public boolean dropPossible(int i) {
        return i != -1;
    }

    public String showStatus() {
        StringBuilder str = new StringBuilder();
        str.append("Status of the player\n")
                .append("load capacity: ")
                .append(loadCapacity)
                .append("kg\n");
        for (Item i : inventory) str.append(i.getName()).append(", ").append(i.getWeight()).append("kg\n");
        str.append("absorbed weight: ")
                .append(currentLoad)
                .append("kg\n");
        return str.toString();
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void goTo(Room newRoom) {
        currentRoom = newRoom;
    }
}
