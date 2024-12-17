package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.make_exam;
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
    @Query("SELECT e FROM exam e WHERE e.idExam =:idExam")
    List<exam> findAllExamsByIdExam(@Param("idExam") Integer idExam);

    @Query("SELECT e FROM exam e WHERE e.nameExam LIKE %:name%")
    List<exam> findAllExamsByName(@Param("name") String name);
    @Query("SELECT e FROM exam e WHERE e.idExam = :query OR e.nameExam LIKE %:query%")
    List<exam> findAllExamsByIdOrName(@Param("query") String query);
    @Query("SELECT e FROM exam e WHERE e.user.id = :userId AND e.idExam = :idExam")
    List<exam> findExamsByUserAndIdExam(@Param("userId") String userId, @Param("idExam") Integer idExam);

    @Query("SELECT e FROM exam e WHERE e.user.id = :userId AND LOWER(e.nameExam) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<exam> findExamsByUserAndName(@Param("userId") String userId, @Param("name") String name);


}


