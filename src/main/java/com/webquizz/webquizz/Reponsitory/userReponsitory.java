package com.webquizz.webquizz.Reponsitory;

import com.webquizz.webquizz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface userReponsitory extends JpaRepository<user, Long> {
    Optional<user> findById(Long id);
    user findByTaikhoan(String taikhoan);
    user findByTaikhoanAndMatkhau1(String taikhoan, String passwordCode);

}
