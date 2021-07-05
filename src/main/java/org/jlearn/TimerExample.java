package org.jlearn;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TimerExample {

    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String[] args) throws InterruptedException {
        startReport();
        Timer timer = metrics.timer("Sleep timer");
        for(int i = 0; i < 100; i++) {
            Timer.Context context = timer.time();
            TimeUnit.SECONDS.sleep(2);
            context.close();
        }
        new Scanner(System.in).nextLine();
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }
}

/*
* Final ConsoleReporter Snippet Post 100 method calls are done
* -- Timers ----------------------------------------------------------------------
Sleep timer
             count = 100
         mean rate = 1.97 calls/second
     1-minute rate = 1.89 calls/second
     5-minute rate = 1.82 calls/second
    15-minute rate = 1.81 calls/second
               min = 499.71 milliseconds
               max = 523.10 milliseconds
              mean = 507.57 milliseconds
            stddev = 5.73 milliseconds
            median = 506.23 milliseconds
              75% <= 511.66 milliseconds
              95% <= 517.73 milliseconds
              98% <= 521.07 milliseconds
              99% <= 521.84 milliseconds
            99.9% <= 523.10 milliseconds
*/