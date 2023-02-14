package de.szut.zuul;

import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
    }

    public void setItem(Item item) {
        this.items.put(item.getName(), item);
    }


    public Room getExit(String direction)
    {

        return exits.get(direction);
    }

    public String exitsToString() {

        StringBuilder str = new StringBuilder();
        exits.forEach((String key, Room val) -> str.append(key).append(" "));
        str.append("\n");

        return str.toString();
    }

    public String ItemsToString() {
        StringBuilder str = new StringBuilder();
        items.forEach((String key, Item item) -> str.append("\t - ")
                .append(key)
                .append(", ")
                .append(item.getDescription())
                .append(", weight: ")
                .append(item.getWeight())
                .append("kg\n"));

        return str.toString();
    }

    Item removeItem(String name) {
        try {
            Item i = items.get(name);
            items.remove(name);
            return i;
        } catch (Exception e) {
            return null;
        }
    }

    public void setExit(String direction, Room room)
    {
        this.exits.put(direction, room);
    }

    Item removeItem(String name) {
        if (!itemInRoom(name)) return null;
        Item i = items.get(name);
        items.remove(name);
        return i;
    }

    public boolean itemInRoom(String name) {
        return items.get(name) != null;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getLongDescription() {
        StringBuilder str = new StringBuilder();
        str.append("You are on the ")
                .append(description)
                .append("\nExits: ")
                .append(exitsToString())
                .append("Items in this room:\n")
                .append(ItemsToString());
        return str.toString();
    }

}
