package ua.hubanov.application.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Skill")
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}