package com.example.webtutorial.springbootwebtutorial.repository;
import com.example.webtutorial.springbootwebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
//    List<EmployeeEntity> findByName(String name);
}
