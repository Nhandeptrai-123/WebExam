package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.exam;
import com.webquizz.webquizz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface userReponsitory extends JpaRepository<user,String> {
    Optional<user> findById(String id);
    user findByTaikhoan(String taikhoan);
    user findByTaikhoanAndMatkhau1(String taikhoan, String passwordCode);
    @Query("SELECT u FROM user u")
    List<user> findAllUser();

}
