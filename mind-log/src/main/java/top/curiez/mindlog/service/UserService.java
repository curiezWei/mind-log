package top.curiez.mindlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.curiez.mindlog.mapper.UserInfoMapper;
import top.curiez.mindlog.model.UserInfo;
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public UserInfo selectByName(String userName) {
        return userInfoMapper.selectByName(userName);
    }
}
