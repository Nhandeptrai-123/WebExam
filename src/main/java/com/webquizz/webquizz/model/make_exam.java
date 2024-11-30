package com.webquizz.webquizz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "make_exam")
public class make_exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Cột ID tự động tăng
    private Long id;

    @Column(name = "id_user")
    private String user; // Người dùng tham gia kỳ thi

    @Column(name = "id_exam")
    private String exam; // Kỳ thi mà người dùng thực hiện

    @Column(name = "ketqua")
    private String ketqua; // Kết quả của bài kiểm tra

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Thời gian tạo bản ghi
    @Transient // Đánh dấu trường này không cần lưu vào cơ sở dữ liệu
    private String examName; // Thêm trường này để chứa tên bài kiểm tra
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Tự động gán thời gian khi tạo
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return createdAt.format(formatter);
    }
}
