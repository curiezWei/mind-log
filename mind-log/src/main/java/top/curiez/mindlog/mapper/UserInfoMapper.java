package top.curiez.mindlog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.curiez.mindlog.model.UserInfo;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where delete_flag = 0 and user_name = #{userName}")
    UserInfo selectByName(String userName);

    @Select("select * from user_info where delete_flag = 0 and id = #{id}")
    UserInfo selectById(Integer id);


}
