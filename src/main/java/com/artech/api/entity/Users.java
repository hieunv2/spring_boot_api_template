package com.artech.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "fullname", length = 45)
    private String fullName;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "username", length = 45)
    private String userName;
    @Column(name = "password", length = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "status", columnDefinition = "int default 1")
    private int status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Orders> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = {@JoinColumn(name = "role_id") })
    private List<Role> roles;


}
