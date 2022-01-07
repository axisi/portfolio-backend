package com.example.portfolio.model;

import com.sun.istack.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Email
    @Size(max = 70)
    private String email;
    @NotBlank
    @Size(max = 200)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(     name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn (name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String bLogin, String bEmail, String bPassword) {
        this.username = bLogin;
        this.email = bEmail;
        this.password = bPassword;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }
    public static class UserBuilder{
        private String bLogin;
        private String bEmail;
        private String bPassword;

        public UserBuilder setLogin (final String login){
            this.bLogin = login;
            return this ;
        }
        public UserBuilder setEmail (final String email){
            this.bEmail = email;
            return this ;
        }
        public UserBuilder setPassword (final String password){
            this.bPassword = password;
            return this ;
        }
        public User build(){
            return new User(bLogin,bEmail,bPassword);
        }

    }

    public User(Long id, String login, String email, String password) {
        this.id = id;
        this.username = login;
        this.email = email;
        this.password = password;
    }




    protected User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
