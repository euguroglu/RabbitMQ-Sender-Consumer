package com.enesuguroglu;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection()) {

            //Creating channel to generate queue
            Channel channel = connection.createChannel();
            //Create queue
            channel.queueDeclare("hello-world", false, false, false, null);

            //Send message to the queue
            String message = "is this the matrix?" + LocalDateTime.now();
            //"" for exhange means default exchange which is direct
            channel.basicPublish("","hello-world", false, null, message.getBytes(StandardCharsets.UTF_8));

            System.out.println("Message has been sent");
        }

    }
}
