package com.community;

import com.community.dao.MessageMapper;
import com.community.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//以CommunityApplication为配置类运行
@ContextConfiguration(classes = CommunityApplication.class)
public class MessageTests {
    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelectConversations() {
        List<Message> list = messageMapper.selectConversations(111, 0, 20);
        for (Message m : list) {
            System.out.println(m);
        }
    }

    @Test
    public void testSelectConversationCount() {
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);
    }

    @Test
    public void testSelectLetters() {
        List<Message> list = messageMapper.selectLetters("111_112", 0, 10);
        for (Message m : list) {
            System.out.println(m);
        }
    }

    @Test
    public void testSelectLetterCount() {
        int count = messageMapper.selectLetterCount("111_112");
        System.out.println(count);

    }

    @Test
    public void testSelectLetterUnreadCount() {
        int count = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(count);
    }
}
