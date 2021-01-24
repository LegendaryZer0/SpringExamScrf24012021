package ru.itis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.model.Simple_user;
import ru.itis.demo.repository.UserInterface;

@Service
public class UserService {
    @Autowired
    private UserInterface userInterfaceRepository;

    public Simple_user catchUser(Integer id){
        return  userInterfaceRepository.findById(id).orElse(Simple_user.builder().email("Wheres no user here").build());
    }
}
