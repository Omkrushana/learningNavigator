// package com.crio.navigator.entity;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter@Setter
// public class StudentExam {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "studentId")
//     private Student student;

//     @ManyToOne
//     @JoinColumn(name = "examId")
//     private Exam exam;
    
// }