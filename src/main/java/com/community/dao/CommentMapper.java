package com.community.dao;

import com.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 查询帖子
    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);

    // 查询条数
    int selectCountByEntity(int entityType, int entityId);


}
