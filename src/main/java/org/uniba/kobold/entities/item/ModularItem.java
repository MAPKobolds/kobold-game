package org.uniba.kobold.entities.item;

import java.io.IOException;
import java.util.*;

public class ModularItem extends Item {
    Set<Class> pieces = new HashSet<>();
    Set<Class> actualPieces = new HashSet<>();

    public ModularItem(String name, String description, String imagePath, List<Class> pieces) throws IOException {
        super(name, description, imagePath);

        for(Class item : pieces) {
            if(!Item.class.isAssignableFrom(item)) {
                throw new Error("This list does not contains only items: " + item.getName());
            }

            int matchCount = pieces.stream().filter(p -> p.equals(item)).toArray().length;

            if(matchCount > 1) {
                throw new Error("Pieces must be unique");
            }
        }

        this.pieces.addAll(pieces);
    }

    public void addPiece(Class item) {
        if(pieces.stream().noneMatch(p -> Objects.equals(p, item))){
            throw new Error("Invalid piece");
        }

        actualPieces.add(item);
    }

    public boolean isComplete() {
        return (this.getRemainingPieces()).toArray().length == 0;
    }

    public List<Class> getActualPieces() {
        return actualPieces.stream().toList();
    }

    public List<Class> getRemainingPieces() {
        Set<Class> dummyPieces = new HashSet<>();
        dummyPieces.addAll(pieces);

        dummyPieces.removeAll(this.actualPieces);
        return dummyPieces.stream().toList();
    }


}
