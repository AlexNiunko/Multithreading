package com.epam.multithreading.entity;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Ship implements Runnable {
    static final Logger logger = LogManager.getLogger();
    static final int capacity = 5;
    private int containerNumber;

    public Ship() {

    }

    public Ship(int containerNumber) {
        this.containerNumber = containerNumber;
    }


    public int getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(int containerNumber) {
        this.containerNumber = containerNumber;
    }

    public int generateContainNumber() {
        Random random = new Random();
        return random.nextInt(capacity);
    }

    public synchronized void takePearce(Port port) throws InterruptedException {
        for (Pearce pearce : port.getPiers()) {
            if (pearce.getStatus().equals(StatusValue.FREE)) {
                pearce.setStatus(StatusValue.BUSY);
                logger.log(Level.INFO, "Ship " + Thread.currentThread().getName() + " took pier number " + pearce.getNumber());
            }
        }
    }


    public synchronized void reload(Port port) throws InterruptedException {
        for (Pearce pear: port.getPiers()) {
            if (pear.getDownload()<this.containerNumber){
                int possibleDownload=Pearce.capacity- pear.getDownload();
                this.containerNumber-=possibleDownload;
                pear.setDownload(pear.getDownload()+possibleDownload);
                logger.log(Level.INFO,"The ship "+Thread.currentThread().getName()+" is unloaded");
            }
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "New ship thread is created. " + Thread.currentThread().getName());
        Port port = Port.getInstance();
        Ship ship = new Ship(generateContainNumber());
        try {
            port.add(ship);
            ship.reload(port);
            port.remove(ship);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
