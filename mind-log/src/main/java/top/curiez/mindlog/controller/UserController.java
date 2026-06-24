package top.curiez.mindlog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.curiez.mindlog.constant.Constants;
import top.curiez.mindlog.model.Result;
import top.curiez.mindlog.model.UserInfo;
import top.curiez.mindlog.service.UserService;
import top.curiez.mindlog.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public Result login(String userName,String password) {
        if(!StringUtils.hasLength(userName)||!StringUtils.hasLength(password)) {
            return Result.fail("账号或密码为空！");
        }
        UserInfo userInfo = userService.selectByName(userName);
        if(userInfo==null) {
            return Result.fail("用户不存在");
        }
        if(!password.equals(userInfo.getPassword())) {
            return Result.fail("密码错误");
        }
        Map<String,Object> claim = new HashMap<>();
        claim.put("id",userInfo.getId());
        claim.put("userName",userInfo.getUserName());
        String jwtToken = JwtUtils.getJwtToken(claim);
        return Result.success(jwtToken);
    }

    @RequestMapping("/guest")
    public Result guest() {
        Map<String,Object> claim = new HashMap<>();
        claim.put("id", 0);
        String jwtToken = JwtUtils.getJwtToken(claim);
        return Result.success(jwtToken);
    }

    @RequestMapping("/getUserInfo")
    public UserInfo getLoginUserInfo(HttpServletRequest request) {
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JwtUtils.getIdByToken(token);
        if(userId==null||userId<=0) {
            UserInfo guestInfo = new UserInfo();
            guestInfo.setUserName("游客");
            return guestInfo;
        }
        UserInfo userInfo = userService.selectById(userId);
        return userInfo;
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId) {
        if(blogId<=0) {
            return null;
        }
        UserInfo userInfo = userService.getAuthorInfo(blogId);
        if(userInfo==null) {
            return null;
        }
        return userInfo;
    }
}
