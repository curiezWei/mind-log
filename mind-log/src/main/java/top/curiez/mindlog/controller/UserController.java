package top.curiez.mindlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.curiez.mindlog.model.Result;
import top.curiez.mindlog.model.UserInfo;
import top.curiez.mindlog.service.UserService;

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

    }
}
