package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface questionRepository extends JpaRepository<question, Integer> {
    @Query("SELECT q FROM question q WHERE q.exam.idExam = :examId")
    List<question> findByExamId(@Param("examId") Integer examId);
    // Nếu bạn cần một phương thức để lấy tất cả câu hỏi, có thể thêm vào đây
    @Query("SELECT e FROM exam e")
    List<exam> findAllExams();
    @Query("SELECT ee FROM exam ee")
    List<exam> findAllExamss();


}

