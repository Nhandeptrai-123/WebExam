package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Request.userRequest;
import com.webquizz.webquizz.model.user;
import com.webquizz.webquizz.config.springSecurity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class dangkyControler {
    @Autowired
    private userRequest userRequest;
    private final springSecurity springSecurity;

    public dangkyControler(springSecurity springSecurity) {
        this.springSecurity = springSecurity;
    }

    @PostMapping("/createuser")
    public String createUser(
            @RequestParam("taikhoan") String taikhoan,
            @RequestParam("mk1") String mk1,
            @RequestParam("mk2") String mk2,
            @RequestParam("dienthoai") String dienthoai,
            @RequestParam("email") String email,
            Model model,
            HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Kiểm tra mật khẩu có trùng khớp không
            if(taikhoan.isEmpty() || mk1.isEmpty() || mk2.isEmpty() || dienthoai.isEmpty()  || email.isEmpty()){
                out.println("<script>alert('Vui lòng nhập đầy đủ thông tin');</script>");
                return "/dangky";
            }
            if (!mk1.equals(mk2)) {
                out.println("<script>alert('Mật khẩu không trùng khớp');</script>");
                return "/dangky";
            }

            // Tạo đối tượng User mới
            user user = new user();
            user.setTaikhoan(taikhoan);
            String passwordCode = springSecurity.passwordEncoder().encode(mk1);
            user.setMatkhau1(passwordCode);
            user.setSodienthoai(dienthoai);
            user.setEmail(email);
            user.setRole("user");

            // Lưu thông tin người dùng vào cơ sở dữ liệu
            userRequest.createUser(user);
            out.println("<script>alert('Đăng ký thành công');</script>");
            return "/dangnhap"; // Chuyển hướng đến trang thành công
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Dữ liệu không hợp lệ: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
        }

        return "/dangky";
    }


}
