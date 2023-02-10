package de.szut.zuul;

public class Item {
    private String name;
    private String description;
    private double weight;

    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public double getWeight() {
        return this.weight;
    }

    public String toString() {
        return name + ", " + description + "weights: " + weight + "kg\n";
    }
}
