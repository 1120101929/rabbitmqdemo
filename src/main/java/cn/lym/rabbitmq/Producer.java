package cn.lym.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuyimin01 on 2017/7/13.
 */
public class Producer extends EndPoint{
    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }


    public void sendMessage(Serializable object) throws IOException {
        super.channel.basicPublish("", super.endPointName, null, SerializationUtils.serialize(object));
    }
}
