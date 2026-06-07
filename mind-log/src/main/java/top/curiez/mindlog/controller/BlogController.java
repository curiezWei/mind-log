package top.curiez.mindlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.curiez.mindlog.model.BlogInfo;
import top.curiez.mindlog.service.BlogService;

import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfo> getList() {
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfo getBlogDetail(Integer blogId) {
        return blogService.getBlogDetail(blogId);
    }
}
