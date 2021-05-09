package com.community.util;

public class RedisKeyUtil {
    private static final String SPILT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    // 生成某个实体的赞
    // like:entity:entityType:entityId -> set(UserId)
    public static String getEntityLikeKey(int entityType, int entityId) {

        return PREFIX_ENTITY_LIKE + SPILT + entityType + SPILT + entityId;
    }

}
