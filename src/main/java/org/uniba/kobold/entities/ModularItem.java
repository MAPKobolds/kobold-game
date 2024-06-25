package org.uniba.kobold.entities;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ModularItem extends Item {
    Set<Item> pieces;
    Set<Item> actualPieces = new HashSet<>();

    ModularItem(String name, String description, String imagePath, List<Item> pieces) throws IOException {
        super(name, description, imagePath);

        for(Item item : pieces) {
            int matchCount = pieces.stream().filter(p -> Objects.equals(p.getName(), item.getName())).toArray().length;

            if(matchCount > 1) {
                throw new Error("Pieces must be unique");
            }
        }

        pieces.addAll(pieces);
    }

    void addPiece(Item item) {
        if(pieces.stream().noneMatch(p -> Objects.equals(p.getName(), item.getName()))){
            throw new Error("Invalid piece");
        }

        actualPieces.add(item);
    }

    boolean isComplete() {
        boolean isComplete = true;

        for (Item item : pieces) {
            if (!actualPieces.contains(item)) {
                isComplete = false;
                break;
            }
        }

        return isComplete;
    }
}
