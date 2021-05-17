package com.community.dao;

import com.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    // 查询用户会话列表，针对每个会话只返回最新的一条私信
    List<Message> selectConversations(int userId, int offset, int limit);

    // 查询用户会话总个数
    int selectConversationCount(int userId);

    // 私信详情
    List<Message> selectLetters(String conversationId, int offset, int limit);

    // 某个回话的私信数量
    int selectLetterCount(String conversationId);

    // 未读消息数量(conversationId==null表示查询总的未读数量)
    int selectLetterUnreadCount(int userId, String conversationId);

    // 新增一个私信
    int insertMessage(Message message);

    // 消息已读未读状态更改
    int updateStatus(List<Integer> ids, int status);

    // 用户删除私信的数量
    int selectLetterDeletedCount(int userId, String conversationId);


}
