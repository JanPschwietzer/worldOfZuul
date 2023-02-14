package de.szut.zuul;

public class Player {
    Room currentRoom;


    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void goTo(Room newRoom) {
        currentRoom = newRoom;
    }
}
