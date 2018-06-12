package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class User {
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_NAME")
    @Size(min = 2, message = "User name should be more than 2 characters.")
    private String name;

    @Column(name = "CONTACT_NO")
    @NotNull(message = "Contact number is a required field!!")
    private String contactNo;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
