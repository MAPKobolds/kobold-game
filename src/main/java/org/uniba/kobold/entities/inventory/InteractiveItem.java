package org.uniba.kobold.entities.inventory;

import org.uniba.kobold.entities.InteractionResult;

/**
 * The interface Interactive item.
 */
public interface InteractiveItem  {

    /**
     * Interact interaction result.
     *
     * @return the interaction result
     */
    InteractionResult interact();

}
