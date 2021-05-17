package com.rabbit.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Producer {
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
            connection = connectionFactory.newConnection("生产者");
            // 3:通过连接获取通道channel
            channel = connection.createChannel();
            // 4:通过通创建交换机,声明队列,绑定关系,路由Key,发送消息和接收消息
            /**
             * @params1队列的名称
             * @params2是否要持久化durable-false所谓持久化消息是否存储
             * @params3排他性，是否是独占独立
             * @params4是否自动删除，随者最后一个消费者消息完毕消息以后是否把队列自动删除
             * @params5携册附属参教
             */
            String queueName = "queue1";
            channel.queueDeclare(queueName,false,false,false,null);
            // 5:准备消息内容
            String message = "Hello rabbitMQ";
            // 6:发送消息给队列queue
            channel.basicPublish("",queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            System.out.println("发送成功！");

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
