package top.curiez.mindlog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void selectByName() {
        System.out.println(userInfoMapper.selectByName("curiez"));
    }

    @Test
    void selectById() {
        System.out.println(userInfoMapper.selectById(2));
    }
}