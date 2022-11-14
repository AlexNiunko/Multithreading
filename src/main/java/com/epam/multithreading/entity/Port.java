package com.epam.multithreading.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Port {
    static final Logger logger = LogManager.getLogger();
    static final int numberOfPearce = 2;
    private static List<Pearce> piers=new ArrayList<>();
    private static Port portInstance;
    private List<Ship> shipList = new ArrayList<>();
    private int shipCounter = 0;


    public static Port getInstance() {
        if (portInstance == null) {
            portInstance = new Port();
            for (int i = 0; i < numberOfPearce; i++) {
                piers.add(new Pearce(i));
            }
        }
        return portInstance;
    }

    public synchronized void add(Ship ship) throws InterruptedException {
        if (shipCounter < numberOfPearce) {
            notifyAll();
            shipList.add(ship);
            logger.log(Level.INFO, "The ship entered the port "+Thread.currentThread().getName());
            shipCounter++;
        } else {
            logger.log(Level.INFO, "All piers are busy");
            wait();
        }
    }

    public synchronized void remove(Ship ship) {
        if (shipCounter > 0) {
            notifyAll();
            shipCounter--;
            shipList.remove(ship);
            logger.log(Level.INFO,"The ship leaved the port "+Thread.currentThread().getName());
        }
    }

    public List<Pearce> getPiers() {
        return piers;
    }
}
