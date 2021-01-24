package ru.itis.demo.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Slf4j
@Component
public class SimpleScrfInterceptor implements HandlerInterceptor {
    private HashSet<String> csrfTokens = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        log.info("Првет из scrf interceptor");
        if (httpRequest.getMethod().equals("POST")) {
            String requestCsrf = httpRequest.getParameter("_csrf_token");
            log.info("Метод POST, вытащенный из параметров токен токен {}",requestCsrf);
            if (((Set<String>) httpRequest.getSession(false).getAttribute("_csrf_tokens")).contains(requestCsrf)) {

                return true;
            } else {
                log.info("csrf token {}, несовпал или отсутсвует "+requestCsrf);
                try {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }


        if(httpRequest.getMethod().equals("GET")){
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token",csrf);
            request.getSession().setAttribute("_csrf_token",csrf);
            log.info("Метод GET, вставленный в сессию токен {}",csrf);

            if( httpRequest.getSession().getAttribute("_csrf_tokens")==null){
                log.info("csrf tokens hashset is null");
                csrfTokens = new HashSet<>();//если там ничего не было, то я создам новый сет csrf токенов
                csrfTokens.add(csrf);
                httpRequest.getSession().setAttribute("_csrf_tokens",csrfTokens);


            }else {
                log.info("csrf tokens hashset is not null");
                csrfTokens = (HashSet<String>) httpRequest.getSession().getAttribute("_csrf_tokens");
                csrfTokens.add(csrf);
                httpRequest.getSession().setAttribute("_csrf_tokens",csrfTokens); // а если же что то было, то возьму действующий

            }
            csrfTokens.add(csrf); // и положу в него токен
            httpRequest.getSession().setAttribute("_csrf_tokens",csrfTokens);
            log.info("puted csrf token {}" + csrf);
            log.info("_csrf_tokens {}" +csrfTokens);

            csrfTokens = new HashSet<>();//в конце обновлю




        }

        log.info("Log from csrfFilter");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
