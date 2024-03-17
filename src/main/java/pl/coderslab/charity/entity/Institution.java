package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
