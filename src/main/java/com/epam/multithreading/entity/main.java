package com.epam.multithreading.entity;

import java.util.Random;

public class main {
    public static void main(String[] args) {
        Thread ship1=new Thread(new Ship());
        Thread ship2=new Thread(new Ship());
        Thread ship3=new Thread(new Ship());
        Thread ship4=new Thread(new Ship());

        ship1.start();
        ship2.start();
        ship3.start();
        ship4.start();

    }

}
