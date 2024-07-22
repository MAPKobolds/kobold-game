package org.uniba.kobold.util;
import java.io.File;

/**
 * The type Dir creator.
 */
public class DirCreator {

    /**
     * Create dir boolean.
     *
     * @param path the path
     * @return the boolean
     */
    public static boolean createDir(String path) {
        File theDir = new File(path);

        return theDir.exists() || theDir.mkdirs();
    }
}
