package cn.lym.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuyimin01 on 2017/7/13.
 */
public class Main {
    public static void main(String[] args) throws IOException, TimeoutException {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 1000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }
}
