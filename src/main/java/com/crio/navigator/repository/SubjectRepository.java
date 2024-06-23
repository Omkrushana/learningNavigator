package com.crio.navigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.navigator.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}