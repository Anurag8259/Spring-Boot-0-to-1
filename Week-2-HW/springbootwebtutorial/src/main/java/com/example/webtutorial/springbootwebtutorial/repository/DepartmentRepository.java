package com.example.webtutorial.springbootwebtutorial.repository;

import com.example.webtutorial.springbootwebtutorial.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
