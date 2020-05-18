package com.pracownia.vanet;

public class Timer {
    public static long start;

    public static double liczCzas(long koniec){
        return (koniec - start)/1000.0;
    }


}
