package ru.mizika.gen.utils;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class RestAssuredHelper {

    public static void sleep(int seconds) {
        await().forever().pollDelay(Duration.ofSeconds(seconds)).until(() -> true);
    }

    public static void sleepMillisecond(long millis) {
        await().forever().pollDelay(Duration.ofMillis(millis)).until(() -> true);
    }
}
