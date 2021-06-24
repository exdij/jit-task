package kc.jit_task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    Integer id;
    String username;
    String password;
}
