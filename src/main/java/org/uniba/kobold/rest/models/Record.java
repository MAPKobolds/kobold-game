package org.uniba.kobold.rest.models;

import java.sql.Time;

public class Record extends Base {
    private String name;
    private long time;

    public Record(int id, String name, Time time) {
        this.id = id;
        this.name = name;
        this.time = time.getTime();
    }

    public Record(int id, String name, long time) {
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
