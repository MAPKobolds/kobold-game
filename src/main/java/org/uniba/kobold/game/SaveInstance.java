package org.uniba.kobold.game;

import java.util.ArrayList;
import java.util.List;

/**
 * SaveInstance class to save instances of the game

 */
public class SaveInstance {

    /**
     * Attributes of the class SaveInstance
     */
    private static List<SaveInstance> instances = new ArrayList<>();

    /**
     * Constructor to add the instance to the list
     */
    public SaveInstance() {
        instances.add(this);
    }

    /**
     * Getter for the list of instances
     * @return the list of instances
     */
    public static List<SaveInstance> getInstances() {
        return instances;
    }

}
