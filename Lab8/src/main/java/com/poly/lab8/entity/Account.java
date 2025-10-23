package com.poly.lab8.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Boolean activated = true;

    // 👇 Trường phân quyền
    private Boolean admin = false;

    // 👇 Thêm method thủ công để chắc chắn Lombok không bị lỗi
    public boolean isAdmin() {
        return Boolean.TRUE.equals(this.admin);
    }
}
