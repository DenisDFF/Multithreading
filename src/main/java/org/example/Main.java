package org.example;

import java.time.Duration;
import java.time.Instant;

class Main {
    public static void main(String[] args) {
       Instant startTime = Instant.now();
       new FiveSecond().start();

       while (true) {
           Instant currentTime = Instant.now();
           Duration elapsedDuration = Duration.between(startTime, currentTime);
           long elepsedSecond = elapsedDuration.getSeconds();

           System.out.println("Time work " + elepsedSecond);

           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}







