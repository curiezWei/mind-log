package top.curiez.mindlog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.curiez.mindlog.model.BlogInfo;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BlogInfoMapperTest {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Test
    void selectAll() {
        System.out.println(blogInfoMapper.selectAll());
    }

    @Test
    void selectById() {
        System.out.println(blogInfoMapper.selectById(1));
    }

    @Test
    void updateBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("testTitle");
        blogInfo.setContent("测试测试");
        blogInfo.setUserId(2);
        blogInfo.setId(3);
        blogInfoMapper.updateBlog(blogInfo);
    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle("testTitle");
        blogInfo.setContent("测试测试");
        blogInfo.setUserId(1);
        blogInfoMapper.insertBlog(blogInfo);
    }
}