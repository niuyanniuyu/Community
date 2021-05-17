package com.rabbit.simple;

import com.rabbitmq.client.*;
import java.io.IOException;

public class Consumer {
    public static void main(String[] args) {
        // 1:创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.107.179.117");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/zjj");

        Connection connection = null;
        Channel channel = null;
        try {
            // 2:创建连接connection
            connection = connectionFactory.newConnection("消费者");
            // 3:通过连接获取通道channel
            channel = connection.createChannel();


            // 4:通过通创建交换机,声明队列,绑定关系,路由Key,发送消息和接收消息
            channel.basicConsume("queue1", true, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery message) throws IOException {
                    System.out.println("收到的消息是：" + new String(message.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("接受失败了");
                }
            });
            // 断点
            System.in.read();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 7:关闭连接
            if(channel!=null&&channel.isOpen()){
                try {
                    channel.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            // 8:关闭通道
            if(connection!=null&&connection.isOpen()){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
