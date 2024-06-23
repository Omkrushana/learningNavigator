package com.crio.navigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.navigator.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
