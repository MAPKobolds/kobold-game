package org.uniba.kobold.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The class that manages the timer.
 */
public class TimeManager {

    /**
     * Attributes of the ManageTimer class
     */
    private boolean running = false;
    private int seconds;
    private int minutes;
    private int hours;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int DELAY = 1000;
    private static final int PERIOD = 1000;

    /**
     * Gets instance and starts the timer.
     * @return the instance
     */
    public TimeManager() {
        tick();
    }

    public TimeManager(String time) {
        this.loadTimer(time);
        tick();
    }

    private synchronized void tick() {
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
            }
        };

        timer.scheduleAtFixedRate(taskTimer, DELAY, PERIOD);
    }

    /**
     * Gets the formatted time.
     * @return the time
     */
    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * startTimer Method to load the timer.
     */
    private void loadTimer(final String time) {
        running = true;
        if (time.equals("00:00:00")) {
            seconds = 0;
            minutes = 0;
            hours = 0;
        } else {
            String[] split = time.trim().split(":");
            hours = Integer.parseInt(split[0]);
            minutes = Integer.parseInt(split[1]);
            seconds = Integer.parseInt(split[2]);
        }
    }

    public long getMilliSeconds() {
        return (long) getSeconds() * 1000 + (long) getMinutes() * 60 * 1000 + (long) getHours() * 60 * 60 * 1000;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

}
