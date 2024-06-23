package com.crio.navigator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "subject_id")
    @JoinTable(
        name = "exam_subject",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "student_exam",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
            student.getExams().add(this);
        }
    }

    public void removeStudent(Student student) {
        if (this.students.contains(student)) {
            this.students.remove(student);
            student.getExams().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Exam [id=" + id + ", subject=" + subject + ", students=" + students + "]";
    }
    
}
