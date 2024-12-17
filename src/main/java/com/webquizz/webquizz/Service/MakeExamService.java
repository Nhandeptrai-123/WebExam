package com.webquizz.webquizz.Service;

import com.webquizz.webquizz.Reponsitory.MakeExamRepository;
import com.webquizz.webquizz.Reponsitory.exampleRepository;
import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.make_exam;
import com.webquizz.webquizz.model.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeExamService {

    @Autowired
    private MakeExamRepository makeExamRepository;

    @Autowired
    private exampleRepository exampleRepository;

    public void save(make_exam makeExam) {
        makeExamRepository.save(makeExam);  // Lưu đối tượng make_exam vào cơ sở dữ liệu
    }

    public List<make_exam> getExamsByUserId(String userId) {
        // Lấy danh sách make_exam từ userId
        List<make_exam> makeExams = makeExamRepository.findByUser(userId);

        // Lặp qua danh sách make_exam để lấy thông tin bài kiểm tra từ bảng exam
        for (make_exam exam : makeExams) {
            // Tìm bài kiểm tra theo id_exam từ bảng exam
            exam.setExamName(getExamNameById(exam.getExam()));
        }

        return makeExams;
    }

    // Tìm tên bài kiểm tra theo id_exam
    private String getExamNameById(String examId) {
        Integer idExam = Integer.parseInt(examId);
        List<exam> exams = exampleRepository.findAllExamsByIdExam(idExam); // Giả sử phương thức trả về danh sách
        return (exams != null && !exams.isEmpty()) ? exams.get(0).getNameExam() : "Không tìm thấy";
    }

    public List<make_exam> getAllByExamId(String examId) {
        return makeExamRepository.findByExamId(examId);
    }
}