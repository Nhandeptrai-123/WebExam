package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.Reponsitory.exampleRepository;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class examServiceIPM {
    @Autowired
    private exampleRepository exampleRepository;

    public exam createExam(exam exam){
        return exampleRepository.save(exam);
    }
    public exam findExamById(Integer examId) {
        return exampleRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
    }
    public List<exam> getExamsByUserId(String userId) {
        return exampleRepository.findAllExamsByUserId(userId);
    }

}
