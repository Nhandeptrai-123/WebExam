package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Reponsitory.userReponsitory;
import com.webquizz.webquizz.Service.userService;
import com.webquizz.webquizz.model.user;
import com.webquizz.webquizz.config.springSecurity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class dangnhapControler {
    @Autowired
//    private userRequest userRequest;
    private userReponsitory userReponsitory;
    private final springSecurity springSecurity;

    public dangnhapControler(springSecurity springSecurity) {
        this.springSecurity = springSecurity;
    }

    @PostMapping("/finduser")
    public String login(
            @RequestParam String taikhoan,
            @RequestParam String mk1,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            user user = userReponsitory.findByTaikhoan(taikhoan);
            if (user == null) {
                out.println("<script>alert('Tài khoản không tồn tại');</script>");
                return "dangnhap"; // Sử dụng tên view không phải đường dẫn URL
            } else if (!springSecurity.passwordEncoder().matches(mk1, user.getMatkhau1())) {
                out.println("<script>alert('Sai mật khẩu');</script>");
                return "dangnhap";
            } else {
                session.setAttribute("user", user);
                if ("admin".equals(user.getRole())) {
                    out.println("<script>alert('Đăng nhập thành công với vai trò quản trị viên');</script>");
                    return "redirect:/admin"; // Chuyển đến trang admin nếu role là admin
                } else {
                    out.println("<script>alert('Đăng nhập thành công');</script>");
                    return "redirect:/"; // Chuyển đến trang chính nếu role là user
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Đã xảy ra lỗi');</script>");
            return "dangnhap";
        }
    }
}
