package com.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pearce {
    static final Logger logger = LogManager.getLogger();
    public static int capacity = 50;
    private int download = 0;
    private StatusValue status = StatusValue.FREE;
    private int number;

    public Pearce(int number) {
        this.number = number;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public StatusValue getStatus() {
        return status;
    }

    public void setStatus(StatusValue status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static int getCapacity() {
        return capacity;
    }
}
