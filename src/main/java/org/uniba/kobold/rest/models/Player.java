package org.uniba.kobold.rest.models;

public class Player extends Base {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
