package top.curiez.mindlog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.curiez.mindlog.mapper.BlogInfoMapper;
import top.curiez.mindlog.mapper.UserInfoMapper;
import top.curiez.mindlog.model.BlogInfo;
import top.curiez.mindlog.model.UserInfo;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogInfoMapper blogInfoMapper;
    public UserInfo selectByName(String userName) {
        return userInfoMapper.selectByName(userName);
    }

    public UserInfo selectById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }

    public UserInfo getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo = blogInfoMapper.selectById(blogId);
        if(blogInfo==null&&blogInfo.getUserId()<=0) {
            log.error("图书信息不存在或有错误！,blogId:{}",blogId);
            return null;
        }
        UserInfo userInfo = userInfoMapper.selectById(blogInfo.getUserId());
        return userInfo;
    }
}
