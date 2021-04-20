package com.community.dao;

import com.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DiscussPostMapper {

    // 查询用户帖子，可分页显示
    // userId = 0 表示首页查询，非特定用户查询帖子
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // 查询评论数量
    // @Param 给参数取别名 !!! 若SQL需要动态取条件，且此方法只有一个参数，则必须取别名
    int selectDiscussPostRows(@Param("userId") int userId);

    // 新建帖子
    int insertDiscussPost(DiscussPost discussPost);

    // 查询帖子详情页
    DiscussPost selectDiscussPostById(int id);

}
