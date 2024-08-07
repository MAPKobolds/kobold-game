package org.uniba.kobold.util;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

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
     */
    public TimeManager() {
        tick();
    }

    /**
     * Instantiates a new Time manager.
     *
     * @param time the time
     */
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
     *
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

    /**
     * Gets milli seconds.
     *
     * @return the milli seconds
     */
    public long getMilliSeconds() {
        return (long) getSeconds() * 1000 + (long) getMinutes() * 60 * 1000 + (long) getHours() * 60 * 60 * 1000;
    }

    /**
     * From long to string string.
     *
     * @param milliseconds the milliseconds
     * @return the string
     */
    public static String fromLongToString(long milliseconds) {
        int hrs = (int) (MILLISECONDS.toHours(milliseconds) % 24);
        int min = (int) (MILLISECONDS.toMinutes(milliseconds) % 60);
        int sec = (int) (MILLISECONDS.toSeconds(milliseconds) % 60);

        return String.format("%d:%02d:%02d", hrs, min, sec);
    }

    /**
     * Gets seconds.
     *
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Gets minutes.
     *
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

}
