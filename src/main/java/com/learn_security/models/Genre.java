package com.learn_security.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
