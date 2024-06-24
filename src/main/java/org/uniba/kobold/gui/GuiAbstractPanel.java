package org.uniba.kobold.gui;

import org.uniba.kobold.util.UtilMusic;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class GuiAbstractPanel extends JPanel {

        /**
         * Entry point for every subclass of GuiAbstractPanel
         */
        public void panelManager() {
                initComponents();
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                updateLayout();
                        }
                });
        }

        /**
         * Method to manage the layout of the background panel
         * @param parent          the parent panel
         * @param backgroundPanel the background panel
         */
        public void manageBackgroundLayout(JPanel parent, JPanel backgroundPanel) {
                GroupLayout layout = new GroupLayout(parent);
                parent.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        }

        /**
         * Method to initialize the components of the class GuiAbstractPanel
         */
        public abstract void initComponents();

        public <T> void initComponents(T Object) {
        };
        /**
         * Method to update the layout of the class GuiAbstractPanel
         */
        public void updateLayout(){
        }

}
