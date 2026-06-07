package top.curiez.mindlog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.curiez.mindlog.model.BlogInfo;

import java.util.List;

@Mapper
public interface BlogInfoMapper {

    @Select("select * from blog_info where delete_flag = 0")
    List<BlogInfo> selectAll();

    @Select("select * from blog_info where id = #{id}")
    BlogInfo selectById(Integer id);

    Integer updateBlog(BlogInfo blogInfo);

    @Insert("insert into blog_info (title,content,user_id) value(#{title},#{content},#{userId})")
    Integer insertBlog(BlogInfo blogInfo);
}
