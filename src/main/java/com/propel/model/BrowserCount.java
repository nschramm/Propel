package com.propel.model;

/**
 * Created by U0011960 on 8/31/16.
 */
public class BrowserCount {

    private String name;
    private int count;

    public BrowserCount(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        this.count++;
    }
}
