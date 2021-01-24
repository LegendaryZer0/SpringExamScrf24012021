package ru.itis.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.model.Simple_user;
import ru.itis.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @PostMapping("/catchUser")
    public String getUser(HttpServletRequest request){
        Integer id = (Integer) request.getSession().getAttribute("id");
        log.info(userService.catchUser(id).toString());
       return "Joke";
    }
}
