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
import top.curiez.mindlog.model.Result;
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
    public BlogInfo getBlogDetail(Integer blogId,HttpServletRequest request) {
        BlogInfo blogDetail = blogService.getBlogDetail(blogId);
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        if(userId!=null&&userId==blogDetail.getUserId()) {
            blogDetail.setLoginUser(true);
        }else {
            blogDetail.setLoginUser(false);
        }
        return blogDetail;
    }

    @RequestMapping("/add")
    public Result addBlog(@RequestBody BlogInfo blogInfo, HttpServletRequest request) {
        log.info("添加博客,blogInfo:{}",blogInfo);
        if(!StringUtils.hasLength(blogInfo.getTitle())||!StringUtils.hasLength(blogInfo.getContent())) {
            return Result.fail("内容或标题不能为空！",false);
        }
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        if(userId==null||userId<=0) {
            return Result.fail("游客模式下无法发布内容！");
        }
        blogInfo.setUserId(userId);

        Boolean valid = blogService.insertBlog(blogInfo);
        if(valid == false) {
            return Result.fail("上传发布失败！",false);
        }
        return Result.success(valid);
    }

    @RequestMapping("/update")
    public Result update(BlogInfo blogInfo, HttpServletRequest request) {
        log.info("更新博客,blogId;{}",blogInfo.getId());
        if(blogInfo.getId()==null
                ||!StringUtils.hasLength(blogInfo.getContent())
                ||!StringUtils.hasLength(blogInfo.getTitle())) {
            return Result.fail("标题和内容不能为空",false);
        }
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        if(userId==null||userId<=0) {
            return Result.fail("游客模式下无法进行该操作！");
        }
        blogService.update(blogInfo);
        return Result.success(true);
    }

    @RequestMapping("/delete")
    public Result delete(Integer blogId, HttpServletRequest request) {
        log.info("删除博客,blogId;{}",blogId);
        if(blogId<=0) {
            return Result.fail("不存在该博客，删除失败！");
        }
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        if(userId==null||userId<=0) {
            return Result.fail("游客模式下无法进行该操作！");
        }
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);
        blogService.update(blogInfo);
        return Result.success(true);
    }
}
