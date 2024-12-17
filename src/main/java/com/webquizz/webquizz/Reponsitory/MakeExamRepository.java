package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.make_exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakeExamRepository extends JpaRepository<make_exam, Long> {
    // Các phương thức truy vấn cần thiết
    @Query("SELECT m FROM make_exam m WHERE m.user = :userId")
    List<make_exam> findByUser(@Param("userId") String userId);
    @Query("SELECT m FROM make_exam m WHERE m.user = :userId AND m.exam = :examId")
    List<make_exam> findByUserAndExam(@Param("userId") String userId, @Param("examId") String examId);
    @Query("SELECT m FROM make_exam m WHERE m.exam= :examId")
    List<make_exam> findByExamId(@Param("examId") String examId);

}