package com.webquizz.webquizz.Controler;

import com.webquizz.webquizz.Service.examServiceIPM;
import com.webquizz.webquizz.model.exam;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class repairExam_1Controler {
    @Autowired
    private examServiceIPM examService;
    @GetMapping("/repairExam_1/{idExam}")
    public String checkExam_1Manager(
            HttpSession session,
            @PathVariable Integer idExam,
            Model model){
        exam exam = examService.findExamById(idExam);
        model.addAttribute("exam", exam);
        return "repairExam1";
    }
}
