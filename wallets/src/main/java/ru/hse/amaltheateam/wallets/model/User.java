package ru.hse.amaltheateam.wallets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "person")
@SequenceGenerator(allocationSize = 1, name = "person_seq", sequenceName = "person_seq")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Long id;

    @Column(name = "email")
    private String email;

}
