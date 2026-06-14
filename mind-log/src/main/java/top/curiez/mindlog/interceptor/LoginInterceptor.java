package top.curiez.mindlog.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.curiez.mindlog.constant.Constants;
import top.curiez.mindlog.utils.JwtUtils;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        log.info("从header中获取token:{}",token);
        Claims claims = JwtUtils.parseToken(token);
        if(claims==null) {
            response.setStatus(401);
            //验证失败
            return false;
        }
        return true;
    }
}
