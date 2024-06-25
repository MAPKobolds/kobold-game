package org.uniba.kobold.entities.room;

import org.javatuples.Pair;
import org.uniba.kobold.entities.item.Item;

import java.util.List;
import java.util.Map;


abstract public class Room {
        private final String name;
        private final List<Item> items;
        private final List<Character> characters;
        private final Map<String, Pair<Boolean,List<String>>> interactions;

        public Room(String name, List<Item> items, List<Character> characters, Map<String, Pair<Boolean,List<String>>> interactions) {
            this.name = name;
            this.items = items;
            this.characters = characters;
            this.interactions = interactions;
        }

        public String getName() {
                return name;
        }

        public List<Item> getItems() {
                return items;
        }

        public List<Character> getCharacters() {
                return characters;
        }

        void isAccessible(String command) {
                Pair<Boolean,List<String>> interact = interactions.get(command);
                interact = interact.setAt0(true);
        }

        void isNotAccessible(String command) {
                Pair<Boolean,List<String>> interact = interactions.get(command);
                interact = interact.setAt0(false);
        }

        abstract void interact(String command);

}
