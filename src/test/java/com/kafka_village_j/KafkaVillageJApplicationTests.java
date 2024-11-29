package com.kafka_village_j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka_village_j.log.mongodb.service.MongodbLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class KafkaVillageJApplicationTests {

    @Autowired
    MongodbLogService mongodbLogService;

    @Test
    void contextLoads() {
    }

    @Test
    void insertList() throws JsonProcessingException {
        for (int i = 0; i < 100; i++) {
            String request = "{" +
                    "\"uuid\":\"id" + i + "\"," +
                    "\"name\":\"name" + i + "\"," +
                    "\"age\":\"" + i + "\"" +
                    "}";
            mongodbLogService.create(new ObjectMapper().readTree(request));
        }
    }

    @Test
    void updateList() throws JsonProcessingException {
        for (int i = 0; i < 100; i++) {
            String request = "{" +
                    "        \"filter\": {" +
                    "            \"uuid\": \"id1\"" +
                    "        }," +
                    "        \"action\": {" +
                    "            \"uuid\": \"id2\"," +
                    "            \"name\": \"name2\"," +
                    "            \"age\": \"32\"" +
                    "        }" +
                    "    }";
            mongodbLogService.update(new ObjectMapper().readTree(request));
        }
    }

    @Test
    void incrementList() throws InterruptedException {
        int threadCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++) {
            executorService.submit(() -> {
                String request = "{" +
                        "        \"filter\": {" +
                        "            \"uuid\": \"id1\"" +
                        "        }," +
                        "        \"action\": {" +
                        "            \"age\": -1" +
                        "        }," +
                        "        \"key\": \"$inc\"" +
                        "    }";
                try {
                    mongodbLogService.update(new ObjectMapper().readTree(request));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
    }

    @Test
    void deleteList() throws JsonProcessingException {
        for (int i = 0; i < 100; i++) {
            String request = "{" +
                    "\"filter\": {" +
                    "\"uuid\":\"id" + i + "\"" +
                    "}" +
                    "}";
            mongodbLogService.delete(new ObjectMapper().readTree(request));
        }
    }

}
