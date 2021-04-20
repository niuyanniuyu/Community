package com.community;

import com.community.dao.CommentMapper;
import com.community.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommentTests {
    @Autowired
    private CommentMapper commentMapper;

    // 获取评论
    @Test
    public void selectCommentByEntity() {
        List<Comment> list = commentMapper.selectCommentByEntity(1, 228, 0, 10);
        for (Comment c : list
        ) {
            System.out.println(c);
        }
    }
    // 获取评论数量
    @Test
    public void selectCountByEntity() {
        System.out.println(commentMapper.selectCountByEntity(1,228));
    }

}
