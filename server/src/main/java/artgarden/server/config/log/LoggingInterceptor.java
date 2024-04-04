package artgarden.server.config.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        LocalDateTime currentTime = LocalDateTime.now();
        //클라이언트의 IP 주소 가져오기
        String clientIP = request.getRemoteAddr();

        // Referer 헤더 가져오기(이전 웹 주소)
        String referer = request.getHeader("Referer");
        
        //aws에서 healthcheck하는 로그 안 찍기
        if(!uri.equals("/aws/healthcheck")){
            System.out.println("["+ currentTime + "] controller: " + uri + " IP: " + clientIP);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method is intentionally left blank
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is intentionally left blank
    }
}
