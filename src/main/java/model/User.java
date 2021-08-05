package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {


    private User(String bLogin, String bEmail, String bPassword) {
        this.login = bLogin;
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


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String email;
    private String password;



    protected User() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
