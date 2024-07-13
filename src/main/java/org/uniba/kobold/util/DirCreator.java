package org.uniba.kobold.util;
import java.io.File;

public class DirCreator {

    public static boolean createDir(String path) {
        File theDir = new File(path);

        return theDir.exists() || theDir.mkdirs();
    }
}
