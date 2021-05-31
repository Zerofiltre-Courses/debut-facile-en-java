package com.zerofiltre.parkingbot;

public class Main {


    public static void main(String[] args) {
       int length = 180;
       int width = 120;
       int surface = length * width;
       int perimeter = (length + width) * 2;
       int difference = length - width;
       float ratio = (float) width / length;
       System.out.println("La surface au sol de la voiture est de: " + surface);
       System.out.println("Le périmète de la voiture est de: " + perimeter);
       System.out.println("La différence entre la longueur et la largeur est de  " + difference);
       System.out.println("Le ratio entre la longueur et la largeur est de  " + ratio);

    }
}
