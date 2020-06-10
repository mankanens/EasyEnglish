package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	public Account findByLogin(String login);
}
