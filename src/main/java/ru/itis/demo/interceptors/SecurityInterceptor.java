package ru.itis.demo.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private String url = "/login";
    private String url2 = "/loginIn";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        log.info("Привет из security interceptor");
        if (url.equals(request.getRequestURI().substring(request.getContextPath().length()))
        || url2.equals(request.getRequestURI().substring(request.getContextPath().length()))){
            log.info("path is protected(login), i will do nothing");
            return true;

        }else {
            log.info("Путь не защищен, начинаю работу");
            log.info("Путь: {}",request.getRequestURI().substring(request.getContextPath().length()));
            if (request.getSession().getAttribute("id") != null) {
                return true;
            }else {
                try {
                    response.sendRedirect("/login");
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        log.info("Возвращаю boolean с последнего return {}",false);
        return  false;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
