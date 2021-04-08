package com.example.dams;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class User {
    @Id
    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column
    private String password;

    @Column(nullable = false, length = 45)
    private String role;

    @Column(nullable = false, length = 45)
    private String question1;

    @Column(nullable = false, length = 45)
    private String answer1;

    @Column(nullable = false, length = 45)
    private String question2;

    @Column(nullable = false, length = 45)
    private String answer2;

    @Column(nullable = false, length = 45)
    private String status;

    @Column(nullable = false, length = 45)
    private Integer zipcode;

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}


