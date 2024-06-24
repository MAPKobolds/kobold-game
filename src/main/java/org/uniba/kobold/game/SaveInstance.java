package org.uniba.kobold.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * SaveInstance class to save instances of the game

 */
public class SaveInstance {

    /**
     * Attributes of the class SaveInstance
     */
    private static final ArrayList<SaveInstance> instances = new ArrayList<>();
    private static String saveName;
    private static String saveDate;

    /**
     * Constructor to add the instance to the list
     */
    public SaveInstance() {
        saveName = "Save " + (instances.size() + 1);
        setSaveDate(new Date());
        instances.add(this);
    }

    /**
     * Getter for the list of instances
     * @return the list of instances
     */
    public static ArrayList<SaveInstance> getInstances() {
        return instances;
    }

    /**
     * Setter for the name of the save
     * @param saveName the name of the save
     */
    public void setSaveName(String saveName) {
        SaveInstance.saveName = saveName;
    }

    /**
     * Getter for the name of the save
     * @return the name of the save
     */
    public String getSaveName() {
        return saveName;
    }


    /**
     * Setter for the date of the save
     * @param date the date of the save
     */
    public void setSaveDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        saveDate = formatter.format(date);
    }

    /**
     * Getter for the date of the save
     * @return the date of the save
     */
    public String getSaveDate() {
        return saveDate;
    }
}
