package com.community;

import com.community.util.CommunityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

// JSON格式生成测试
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class JSONTests {

    @Test
    public void TestJSON() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 24);
        System.out.println(CommunityUtil.getJSONString(0, "ok", map));
    }

}
