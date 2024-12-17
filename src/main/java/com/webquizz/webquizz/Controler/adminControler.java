package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Service.examServiceIPM;
import com.webquizz.webquizz.Service.questionServiceIPM;
import com.webquizz.webquizz.Service.userService;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.user;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class adminControler {
    @Autowired
    private examServiceIPM examService;
    @Autowired
    private com.webquizz.webquizz.Service.questionServiceIPM questionServiceIPM;
    @Autowired
    private userService userService;
    // Dịch vụ liên quan đến bài kiểm tra

    // Xử lý xóa người dùng theo ID


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(id);  // Use UUID here instead of Long
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user!");
        }
        return "redirect:/admin";
    }
    @GetMapping("/exam/delete/{id}")
    public String deleteExam(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            examService.deleteByIdExam(id);  // Use UUID here instead of Long
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user!");
        }
        return "redirect:/admin-exam";
    }
    @GetMapping("/grant/{id}")
    public String grantRole(@PathVariable String id, Model model) {
        // Lấy người dùng theo ID
        user user = userService.findById(id);
        if (user != null) {
            // Kiểm tra và thay đổi quyền của người dùng
            if ("admin".equals(user.getRole())) {
                user.setRole("user");  // Nếu người dùng là admin, thay đổi thành user
                model.addAttribute("message", "Đã thay đổi quyền của người dùng " + user.getTaikhoan() + " thành user");
            } else {
                user.setRole("admin");  // Nếu người dùng là user, thay đổi thành admin
                model.addAttribute("message", "Đã thay đổi quyền của người dùng " + user.getTaikhoan() + " thành admin");
            }

            // Lưu thay đổi vào cơ sở dữ liệu
            userService.save(user);

        } else {
            model.addAttribute("message", "Không tìm thấy người dùng");
        }

        return "redirect:/admin";  // Sau khi cấp quyền, chuyển hướng về trang admin
    }




}

