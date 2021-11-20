package com.enesuguroglu;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        Connection connection = factory.newConnection();

        //Creating channel to generate queue
        Channel channel = connection.createChannel();
        //Create queue
        channel.queueDeclare("hello-world", false, false, false, null);

        channel.basicConsume("hello-world", true, (consumerTag, message) -> {
            String m = new String(message.getBody(), "UTF-8");
            System.out.println("I just received a message = " + m);
        }, consumerTag -> { });
        }

}

