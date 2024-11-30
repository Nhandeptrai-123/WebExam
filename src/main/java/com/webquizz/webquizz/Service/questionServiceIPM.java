package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.Reponsitory.exampleRepository;
import com.webquizz.webquizz.Reponsitory.questionRepository;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class questionServiceIPM implements questionService {
    @Autowired
    private questionRepository questionRepository;
    @Autowired
    private com.webquizz.webquizz.Reponsitory.exampleRepository exampleRepository;

    public question createQuestion(question question) {
        return questionRepository.save(question);
    }

    public void deleteById(Integer id) {
        questionRepository.deleteById(id);  // Xóa câu hỏi từ cơ sở dữ liệu
    }

    public List<question> getAllByExamId(Integer examId) {
        return questionRepository.findByExamId(examId);
    }
    public List<exam> searchExams(String query) {
        if (query.matches("\\d+")) {
            // Nếu query là số, tìm theo ID
            Integer idExam = Integer.parseInt(query);
            return exampleRepository.findAllExamsByIdExam(idExam);
        } else {
            // Nếu query là chuỗi, tìm theo tên
            return exampleRepository.findAllExamsByName(query);
        }
    }
    public List<exam> getAllExams() {
        return questionRepository.findAllExams(); // Ensure this method is defined in the repository
    }
    public List<exam> getAllExamss() {
        return questionRepository.findAllExamss(); // Ensure this method is defined in the repository
    }

    public question getQuestionById(Integer id) {
        // Tìm câu hỏi theo ID, nếu không tìm thấy sẽ trả về null
        return questionRepository.findById(id).orElse(null);
    }



}

