package ru.itis.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.demo.model.Simple_user;
@Repository
public interface UserInterface extends CrudRepository<Simple_user,Integer> {

}
