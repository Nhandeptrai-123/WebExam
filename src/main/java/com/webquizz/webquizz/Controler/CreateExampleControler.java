package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Reponsitory.MakeExamRepository;
import com.webquizz.webquizz.Service.examServiceIPM;
import com.webquizz.webquizz.Service.questionServiceIPM;

import com.webquizz.webquizz.model.exam;

import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.System.out;

@Controller
public class CreateExampleControler {

    @Autowired
    private examServiceIPM examService;

    @Autowired
    private questionServiceIPM questionService;
    @Autowired
    private MakeExamRepository makeExamRepository;


    @PostMapping("/createExample_1")
    public String createExample(
            @RequestParam("testName1") String testname1,
            @RequestParam("ExamModal1") String ExamModal1,
            HttpSession session
    ) throws UnsupportedEncodingException {
        // Tạo đối tượng "bài test"
        if(testname1.isEmpty()){
            out.println("<script>alert('Vui lòng nhập tên bài thi');</script>");
            return "redirect:/example_1";
        }
        exam exam = new exam();
        exam.setNameExam(testname1);
        exam.setDateExam(LocalDateTime.now());
        exam.setTest(ExamModal1);
        user user = (user) session.getAttribute("user");
        exam.setUser(user);

        // Lưu dữ liệu của exam
        examService.createExam(exam);

        // Chuyển hướng đến trang Example_1 với IDexam và NameExam trong URL
        return "redirect:/example_1?IDexam=" + exam.getIdExam() + "&NameExam=" + URLEncoder.encode(exam.getNameExam(), "UTF-8");
    }

    @PostMapping("/exam_1/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id,
            @RequestParam("IDexam") Integer examId,
            @RequestParam("NameExam") String NameExam,
            RedirectAttributes redirectAttributes,
            Model model
    ) throws UnsupportedEncodingException {
        model.addAttribute("IDexam", examId);
        model.addAttribute("NameExam", NameExam);
        questionService.deleteById(id);
        return "redirect:/example_1?IDexam=" + examId + "&NameExam=" + URLEncoder.encode(NameExam, "UTF-8");
    }

    @PostMapping("/exam_1")
    public String createQuestion(
            @RequestParam("question") String questionText,
            @RequestParam("answer1") String answer1,
            @RequestParam("answer2") String answer2,
            @RequestParam("answer3") String answer3,
            @RequestParam("answer4") String answer4,
            @RequestParam(value = "selectedAnswer", required = false) String selectedAnswer,
            @RequestParam("IDexam") Integer examId,
            @RequestParam("NameExam") String NameExam,
            Model model
    ) throws UnsupportedEncodingException {
        // Tạo câu hỏi mới
        if(questionText.isEmpty() || answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()||
            answer4.isEmpty() || selectedAnswer.isEmpty()
        ){
            out.println("<script>alert('Vui lòng nhập/chọn đầy đủ');</script>");
            return "redirect:/example_1?IDexam=" + examId + "&NameExam=" + URLEncoder.encode(NameExam, "UTF-8");
        }
        question question = new question();
        question.setQuestion(questionText);
        question.setAnswer1(answer1);
        question.setAnswer2(answer2);
        question.setAnswer3(answer3);
        question.setAnswer4(answer4);
        question.setCorrectAnswer(selectedAnswer);

        // Tìm bài kiểm tra theo ID
        exam exam = examService.findExamById(examId);
        if (exam == null) {
            // Xử lý lỗi không tìm thấy bài kiểm tra
            model.addAttribute("error", "Exam not found");
            return "error"; // trang lỗi tùy chỉnh
        }
        question.setExam(exam);
        // Lưu câu hỏi vào cơ sở dữ liệu
        questionService.createQuestion(question);

        // Chuyển hướng lại trang Example_1 để tránh mất dữ liệu
        return "redirect:/example_1?IDexam=" + examId + "&NameExam=" + URLEncoder.encode(NameExam, "UTF-8");
    }




}
