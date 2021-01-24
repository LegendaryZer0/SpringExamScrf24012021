package ru.itis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class Simple_user {
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String password;
    private boolean is_deleted;
    private String confirm_code;
}
