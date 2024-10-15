package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.question;
import com.webquizz.webquizz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface exampleRepository extends JpaRepository<exam, Integer> {
    Optional<exam> findByidExam(Integer idExam);
    List<exam> findByuser(user user);
    @Query("SELECT e FROM exam e WHERE e.user.id = :userId")
    List<exam> findAllExamsByUserId(@Param("userId") String userId);
}