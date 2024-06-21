package org.uniba.kobold.game;

import java.util.ArrayList;
import java.util.List;

public class SaveInstance {
    private static List<SaveInstance> instances = new ArrayList<>();

    public SaveInstance() {
        instances.add(this);
    }

    public static List<SaveInstance> getInstances() {
        return instances;
    }

}
