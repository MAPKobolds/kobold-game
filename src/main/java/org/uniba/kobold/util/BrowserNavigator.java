package org.uniba.kobold.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The type Browser navigator.
 */
public class BrowserNavigator {
    /**
     * Go to site.
     *
     * @param url the url
     */
    public static void goToSite(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (URISyntaxException | IOException e) {
            throw new Error("Cannot go to the browser");
        }
    }
}
