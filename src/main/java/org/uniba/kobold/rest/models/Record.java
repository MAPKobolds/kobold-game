package org.uniba.kobold.rest.models;

import java.sql.Time;

public class Record extends Base {
    private String name;
    private long time;

    public Record(String name, Time time, int id) {
        this.id = id;
        this.name = name;
        this.time = time.getTime();
    }

    public Record(String name, long time, int id) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
