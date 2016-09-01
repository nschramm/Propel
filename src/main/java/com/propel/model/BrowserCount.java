package com.propel.model;

/**
 * Created by U0011960 on 8/31/16.
 */
public class BrowserCount {

    private String browser;
    private int count;

    public BrowserCount(String browser, int count) {
        this.browser = browser;
        this.count = count;
    }

    public String getBrowser() {
        return browser;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        this.count++;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
