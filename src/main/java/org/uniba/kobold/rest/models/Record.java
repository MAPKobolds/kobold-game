package org.uniba.kobold.rest.models;

import java.sql.Time;

/**
 * The type Record.
 */
public class Record extends Base {
    private String name;
    private long time;

    /**
     * Instantiates a new Record.
     *
     * @param name the name
     * @param time the time
     * @param id   the id
     */
    public Record(String name, Time time, int id) {
        this.id = id;
        this.name = name;
        this.time = time.getTime();
    }

    /**
     * Instantiates a new Record.
     *
     * @param name the name
     * @param time the time
     * @param id   the id
     */
    public Record(String name, long time, int id) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
