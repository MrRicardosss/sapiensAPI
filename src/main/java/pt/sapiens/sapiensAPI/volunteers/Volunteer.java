package pt.sapiens.sapiensAPI.volunteers;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String profilePicture;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String citizenNumber;
}

/*
id int [PK]
fullName varchar [not null]
email varchar [unique, not null]
password varchar [not null]
profilePicture varchar [not null]
birthday date [not null]
phoneNumber varchar
citizenNumber varchar [not null, unique]
*/