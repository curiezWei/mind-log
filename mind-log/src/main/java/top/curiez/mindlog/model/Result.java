package top.curiez.mindlog.model;

import lombok.Data;
import top.curiez.mindlog.enums.ResultStatus;

@Data
public class Result <T>{
    private ResultStatus code;//业务码
    private String errMsg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultStatus.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String errMsg) {
        Result result = new Result();
        result.setCode(ResultStatus.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> fail(String errMsg,T data) {
        Result result = new Result();
        result.setCode(ResultStatus.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }
}
