package com.example.demo.util.mqtt;

import org.junit.Test;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class MqttUtilTest {

    @Test
    public void testSingletonConcurrency() throws InterruptedException {
        int threads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        Set<MqttUtil> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                instances.add(MqttUtil.getInstance());
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals("Multiple instances were created!", 1, instances.size());
    }
}
