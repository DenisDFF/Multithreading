package org.example;

import java.time.Duration;
import java.time.Instant;

public class FiveSecond extends Thread {
    Instant startTime = Instant.now();
    @Override
    public void run() {
        Instant currentTime = Instant.now();
        Duration elapsedDuration = Duration.between(startTime, currentTime);
        long elepsedSecond = elapsedDuration.getSeconds();

        while (true) {
            if (elepsedSecond % 5 == 0 && elepsedSecond > 0) {
                System.out.println("Минуло 5 секунд");
            }

            try {
                Thread.sleep(1000); // Почекати 1 секунду
                elepsedSecond++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
