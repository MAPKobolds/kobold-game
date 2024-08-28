package org.uniba.kobold.gui;

import javax.swing.*;

/**
 * The interface List panel.
 *
 * @param <T> the type parameter
 */
public interface ListPanel<T> {
    /**
     * Create item j panel.
     *
     * @param item the item
     * @param args the args
     * @return the j panel
     */
    JPanel createItem(T item, Integer... args);

    /**
     * Load list.
     */
    void loadList();

    /**
     * Manage no content found.
     */
    void manageNoContentFound();
}
