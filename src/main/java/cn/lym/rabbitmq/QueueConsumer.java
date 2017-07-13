package cn.lym.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuyimin01 on 2017/7/13.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer{
    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    @Override
    public void handleConsumeOk(String consumerTag) {

    }

    @Override
    public void handleCancelOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered.");
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        Map map = (Map) SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("message number") + " received.");
    }

    @Override
    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            super.channel.basicConsume(super.endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
