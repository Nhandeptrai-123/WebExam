package com.webquizz.webquizz.Service;

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

    public question createQuestion(question question) {
        return questionRepository.save(question);
    }

    public void deleteById(Integer id) {
        questionRepository.deleteById(id);  // Xóa câu hỏi từ cơ sở dữ liệu
    }

    public List<question> getAllByExamId(Integer examId) {
        return questionRepository.findByExamId(examId);
    }
}

