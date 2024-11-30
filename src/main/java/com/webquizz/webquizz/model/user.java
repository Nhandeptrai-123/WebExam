package com.webquizz.webquizz.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class user implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "taikhoan")
    private String taikhoan;
    @Column(name = "matkhau1")
    private String matkhau1;
    @Column(nullable = false)
    private String sodienthoai;
    @Column(nullable = false)
    private  String email;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<exam> exam;

    public List<exam> getQuestions() {
        return exam;
    }

    public void setQuestions(List<exam> exam) {
        this.exam = exam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau1() {
        return matkhau1;
    }

    public void setMatkhau1(String matkhau1) {
        this.matkhau1 = matkhau1;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }


    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    @Override
//    public String toString() {
//        return "User{id=" + id + ", username='" + taikhoan + "'}";
//    }
    @Override
    public String toString() {
        return taikhoan; // Chỉ trả về taikhoan
    }


}
