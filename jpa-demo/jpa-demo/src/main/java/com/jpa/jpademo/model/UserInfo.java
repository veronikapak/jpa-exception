package com.jpa.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Gran1 on 28/02/2018.
 */
@Entity
@Table(name = "USER_INFO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false)
    @OneToOne//(mappedBy = "id",cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private String firstName;

    private String lastName;
}
