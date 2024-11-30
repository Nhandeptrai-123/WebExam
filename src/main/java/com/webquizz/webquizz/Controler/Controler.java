package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Reponsitory.MakeExamRepository;
import com.webquizz.webquizz.Service.MakeExamService;
import com.webquizz.webquizz.Service.examServiceIPM;
import com.webquizz.webquizz.Service.questionServiceIPM;

import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.make_exam;
import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Controller
public class Controler {
    @Autowired
    private examServiceIPM examService;
    @Autowired
    private questionServiceIPM questionServiceIPM;

    @Autowired
    private MakeExamService MakeExamService;

    @GetMapping("/")
    public String index(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        user user = (user) session.getAttribute("user");
        if (user == null) {
//            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/signup")
    public String dangky() {
        return "dangky";
    }

    @GetMapping("/login")
    public String dangnhap() {
        return "dangnhap";
    }


    @GetMapping("/example_1")
    public String example1(HttpSession session,
                           Model model,
                           @RequestParam("IDexam") Integer idExam,
                           @RequestParam("NameExam") String nameExam) {
        user user = (user) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("IDuser", user.getId());
        model.addAttribute("IDexam", idExam);
        model.addAttribute("NameExam", nameExam);

        // Kiểm tra có câu hỏi nào cho ID exam này không
        List<question> questions = questionServiceIPM.getAllByExamId(idExam);

        if (questions != null && !questions.isEmpty()) {
            model.addAttribute("questions", questions);
        } else {
            model.addAttribute("questions", Collections.emptyList());
        }
        return "Example_1";
    }

    @GetMapping("/example_2")
    public String example_2(HttpSession session, Model model){
        user user = (user) session.getAttribute("user");
        model.addAttribute("user", user);
        return "Example_2";
    }

    @GetMapping("/createtest")
    public String createtest(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
//        sessionEmail(user, model);
        model.addAttribute("user", user);
        return "createExample";
    }
    @GetMapping("/library")
    public String service(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
        }else{
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        List<exam> examuser = examService.getExamsByUserId(user.getId());
            if (examuser != null && !examuser.isEmpty()) {
            model.addAttribute("examuser", examuser);
        } else {
            model.addAttribute("examuser", Collections.emptyList());
        }
        sessionEmail(user, model);
        model.addAttribute("user", user);
        return "Library";
    }
    @GetMapping("/kiemtra")
    public String kiemTra(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
        }else{
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        List<exam> exams = questionServiceIPM.getAllExams();
        if (exams != null && !exams.isEmpty()) {
            model.addAttribute("exams", exams);
        } else {
            model.addAttribute("exams", Collections.emptyList());
        }
        sessionEmail(user, model);
        model.addAttribute("user", user); // Add the list of questions to the model
        return "kiemtra"; // Return the view kiemtra.html
    }
    @GetMapping("/questions/{idExam}")
    public String getQuestionsByExamId(@PathVariable Integer idExam, HttpSession session, Model model) {
        // Lấy thông tin người dùng từ session
        user user = (user) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }

        // Lấy danh sách câu hỏi theo idExam
        List<question> questions = questionServiceIPM.getAllByExamId(idExam);
        model.addAttribute("questions", questions); // Thêm danh sách câu hỏi vào model

        // Truyền idExam và idUser vào model để sử dụng trong Thymeleaf
        model.addAttribute("idExam", idExam);
        model.addAttribute("idUser", user.getId());

        return "questions"; // Trả về trang questions.html
    }
    @GetMapping("/my-questions")
    public String myQuestions(HttpSession session, Model model) {
        // Lấy thông tin người dùng từ session
        user user = (user) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("loggedIn", false);
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }

        // Lấy danh sách bài kiểm tra mà người dùng đã thực hiện
        List<make_exam> userExams = MakeExamService.getExamsByUserId(user.getId());

        // Thêm danh sách bài kiểm tra vào model
        if (userExams != null && !userExams.isEmpty()) {
            model.addAttribute("userExams", userExams);
        } else {
            model.addAttribute("userExams", Collections.emptyList());
        }

        // Thêm thông tin người dùng vào model
        sessionEmail(user, model);
        model.addAttribute("user", user);

        return "my-questions"; // Trả về trang my-questions.html
    }










    @GetMapping("/contact")
    public String contact(HttpSession session, Model model) {
        user user = (user) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("loggedIn", false);
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
//        sessionEmail(user, model);
        model.addAttribute("user", user);
        return "contact";
    }

    public Object sessionEmail(user user, Model model) {
        if (user == null) {
//            return "redirect:/login"; // Chuyển hướng về trang đăng nhập nếu không có session
            model.addAttribute("loggedIn", false);
            return "redirect:/login";
        } else {
            model.addAttribute("username", user.getTaikhoan());
            model.addAttribute("loggedIn", true); // Đặt cờ loggedIn là true
        }
        return null;
    }
}
