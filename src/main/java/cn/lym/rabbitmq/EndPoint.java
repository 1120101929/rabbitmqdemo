package cn.lym.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Represents a connection with a queue
 * Created by liuyimin01 on 2017/7/13.
 */
public abstract class EndPoint {
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endPointName) throws IOException, TimeoutException {
        this.endPointName = endPointName;

        //create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        //hostname of the rabbitmq server
        String host = "123.56.220.18";
        factory.setHost(host);
        factory.setUsername("test");
        factory.setPassword("test");
        //getting a connection
        this.connection = factory.newConnection();
        //creating a channel
        this.channel = this.connection.createChannel();
        //declaring a queue for this channel. If queue does not exist, it will be created on the server
        this.channel.queueDeclare(endPointName, false, false, false, null);
    }

    /**
     * 关闭channel和Connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
