package cn.lym.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuyimin01 on 2017/7/13.
 */
public class ReceiverMain {
    public static void main(String[] args) throws IOException, TimeoutException {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
