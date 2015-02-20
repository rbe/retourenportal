package eu.artofcoding.retoure.delivery.tomee;

import org.junit.Test;

public class WaitForContainerIT {

    private static final int WAIT = 20;

    @Test
    public void shouldStartContainer() {
        System.out.printf("Waiting %d seconds for container to startup%n", WAIT);
        try {
            Thread.sleep(WAIT * 1000);
        } catch (InterruptedException e) {
            System.out.printf("Interrupted waiting for container to startup: %s%n", e.getMessage());
        }
    }

}
