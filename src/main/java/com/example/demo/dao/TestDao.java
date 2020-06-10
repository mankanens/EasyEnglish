package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Test;

@Repository
public interface TestDao extends JpaRepository<Test, Long> {

	public Test findByCode(String code);

}
