package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.model.question;

import java.util.List;

public interface questionService {
    question createQuestion(question question);
    // Thêm phương thức lấy câu hỏi theo ID
    question getQuestionById(Integer id);

}
