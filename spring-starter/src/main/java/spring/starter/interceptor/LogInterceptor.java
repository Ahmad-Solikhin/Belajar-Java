package spring.starter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    //Ini serbelum masuk controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Sebelum");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //Setelah di proses controller
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Sesudah");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //Callback method kalo udah dirender
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Callback");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
