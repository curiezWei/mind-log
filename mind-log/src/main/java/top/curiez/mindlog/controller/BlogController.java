package top.curiez.mindlog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.curiez.mindlog.constant.Constants;
import top.curiez.mindlog.model.BlogInfo;
import top.curiez.mindlog.service.BlogService;
import top.curiez.mindlog.utils.JwtUtils;

import java.util.List;
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfo> getList() {
        log.info("获取博客列表");
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId) {
        return blogService.getBlogDetail(blogId);
    }

    @RequestMapping("/add")
    public boolean addBlog(@RequestBody BlogInfo blogInfo, HttpServletRequest request) {
        log.info("添加博客,blogInfo:{}",blogInfo);
        if(!StringUtils.hasLength(blogInfo.getTitle())||!StringUtils.hasLength(blogInfo.getContent())) {
            return false;
        }
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        blogInfo.setUserId(userId);
        return blogService.insertBlog(blogInfo);
    }
}
