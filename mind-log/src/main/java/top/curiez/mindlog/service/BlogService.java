package top.curiez.mindlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.curiez.mindlog.mapper.BlogInfoMapper;
import top.curiez.mindlog.model.BlogInfo;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;
    public List<BlogInfo> getList() {
        return blogInfoMapper.selectAll();
    }

    public BlogInfo getBlogDetail(Integer blogId) {
        return blogInfoMapper.selectById(blogId);
    }

    public Boolean insertBlog(BlogInfo blogInfo) {
        Integer i = blogInfoMapper.insertBlog(blogInfo);
        if(i==1) {
            return true;
        }
        return false;
    }
}
