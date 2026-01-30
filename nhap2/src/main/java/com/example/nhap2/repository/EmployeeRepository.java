package com.example.nhap2.repository;

import com.example.nhap2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e FROM Employee e WHERE " +
           "(:name IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:deptId IS NULL OR e.department.id = :deptId)")
    List<Employee> search(@Param("name") String name, @Param("deptId") Long deptId);
}
