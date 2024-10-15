package com.webquizz.webquizz.Service;

import org.springframework.stereotype.Service;

@Service
public class userService {
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
}
