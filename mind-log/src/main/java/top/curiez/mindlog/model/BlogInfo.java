package top.curiez.mindlog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.curiez.mindlog.utils.DateUtils;

import java.util.Date;

@Data
public class BlogInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
//    public String getCreateTime() {
//        return DateUtils.dateFormat(createTime);
//    }
}
