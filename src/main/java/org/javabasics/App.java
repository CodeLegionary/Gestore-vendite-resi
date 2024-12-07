package org.javabasics;

import org.javabasics.controller.Controller;

public class App
{

    public static void main(String[] args)
    {
    System.out.println("Benvenuto nel sistema!");
    Controller controllore= new Controller();
    controllore.start();
    }
}