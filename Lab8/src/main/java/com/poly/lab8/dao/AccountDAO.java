package com.poly.lab8.dao;

import com.poly.lab8.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, String> {
    // ✅ Thêm dòng này để Spring JPA tự tạo truy vấn theo tên hàm
    Account findByUsername(String username);
}
