package org.uniba.kobold.gui;

import javax.swing.*;

public interface ListPanel<T> {
    JPanel createItem(T item, Integer... args);

    void loadList();

    void manageNoContentFound();
}
