package com.poly.lab8.service.impl;

import com.poly.lab8.dao.AccountDAO;
import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO dao;

    @Override
    public Account findById(String username) {
        return dao.findById(username).orElse(null);
    }

    // ✅ Bổ sung phương thức này để implement interface
    @Override
    public Account findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }
}
