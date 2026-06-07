package top.curiez.mindlog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.curiez.mindlog.model.Result;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler
    public Result handle(Exception e) {
        log.error("异常发生,e:",e);
        return Result.fail("发生错误，请联系我！");
    }
}
