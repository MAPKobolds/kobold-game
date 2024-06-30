package org.uniba.kobold.util;
import org.uniba.kobold.gui.GuiGame;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The class that manages the timer.
 */
public class ManageTimer {

    /**
     * Attributes of the ManageTimer class
     */
    private static ManageTimer instance;
    private static boolean running = false;
    private static int seconds;
    private static int minutes;
    private static int hours;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int DELAY = 1000;
    private static final int PERIOD = 1000;

    /**
     * Gets instance and starts the timer.
     * @return the instance
     */
    public static synchronized ManageTimer getInstance() {
        if (instance == null && !running) {
            instance = new ManageTimer();
            Timer timer = new Timer();
            TimerTask taskTimer = new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    if (seconds == SECONDS_IN_MINUTE) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes == MINUTES_IN_HOUR) {
                        minutes = 0;
                        hours++;
                    }
                    GuiGame.setTimeLabel(getTime());
                }
            };
            timer.scheduleAtFixedRate(taskTimer, DELAY, PERIOD);
        }
        return instance;
    }

    /**
     * Gets the formatted time.
     * @return the time
     */
    public static String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * startTimer Method to start the timer.
     */
    public void startTimer(final String time) {
        running = true;
        if (time.equals("00:00:00")) {
            seconds = 0;
            minutes = 0;
            hours = 0;
            GuiGame.setTimeLabel("00:00:00");
        } else {
            String[] split = time.trim().split(":");
            hours = Integer.parseInt(split[0]);
            minutes = Integer.parseInt(split[1]);
            seconds = Integer.parseInt(split[2]);
            GuiGame.setTimeLabel(time.trim());

        }
    }
}