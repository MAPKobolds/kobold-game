package org.uniba.kobold.util;

import org.javatuples.Pair;

public class ComparePair {
    public static boolean isPairEquals(Pair a, Pair b) {
        return a.getValue0().equals(b.getValue0()) && a.getValue1().equals(b.getValue1());
    }
}
