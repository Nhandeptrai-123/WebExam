package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.Reponsitory.questionRepository;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    private com.webquizz.webquizz.Reponsitory.userReponsitory userReponsitory;
    private String taikhoan;
    private String matkhau1;
    private String sodienthoai;
    private  String email;

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

    public void setEmail(String email) {
        this.email = email;
    }
    public List<user> getAllUser() {
        return userReponsitory.findAllUser(); // Ensure this method is defined in the repository
    }
    public void deleteUserById(String id) {
        userReponsitory.deleteById(id);  // Assuming userReponsitory extends JpaRepository
    }
    public user findById(String id) {
        return userReponsitory.findById(id).orElse(null);
    }

    public void save(user user) {
        userReponsitory.save(user);
    }
}
