package com.webquizz.webquizz.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "exam")
public class exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam") // Đảm bảo tên cột khớp với cơ sở dữ liệu
    private Integer idExam;

    @Column(name = "name_exam")
    private String nameExam;

    @Column(name = "test")
    private String test;

    @Column(name="date_exam")
    private LocalDateTime dateExam;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")  // Đảm bảo đúng tên cột idUser
    private user user;

    public Integer getIdExam() {
        return idExam;
    }

    public String getFormattedDateExam() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateExam.format(formatter);
    }

    public void setDateExam(LocalDateTime dateExam) {
        this.dateExam = dateExam;
    }

    public void setIdExam(Integer idExam) {
        this.idExam = idExam;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public com.webquizz.webquizz.model.user getUser() {
        return user;
    }

    public void setUser(com.webquizz.webquizz.model.user user) {
        this.user = user;
    }

}
