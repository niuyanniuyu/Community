package com.community.dao;

import com.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * 在dao层直接使用注解开发
 * 不使用mapper.xml文件开发
 * <p>
 * 使用Redis存储登录凭证，暂不推荐使用此Mapper
 * </p>
 */

@Mapper
@Deprecated
public interface LoginTicketMapper {

    // 插入登录数据
    // 将“”拼接成SQL语句,注意换行时末尾加个空格
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    // 自动将id主键生成
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);


    // 查询Ticket
    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    // 修改凭证状态
    // 此处演示动态SQL实例
    @Update({
            "<script>",
            "update login_ticket set status=#{status} ",
            "where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);


}
