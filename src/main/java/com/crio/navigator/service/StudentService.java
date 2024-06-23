package com.crio.navigator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// import com.crio.navigator.entity.Enrollment;
import com.crio.navigator.entity.Student;
import com.crio.navigator.entity.Subject;
// import com.crio.navigator.repository.EnrollmentRepository;
import com.crio.navigator.repository.StudentRepository;
import com.crio.navigator.repository.SubjectRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // @Autowired
    // private SubjectRepository subjectRepository;

    // @Autowired
    // private EnrollmentRepository enrollmentRepository;

    // public boolean enrollInSubject(Long studentId, Long subjectId) {
    // Optional<Student> student = studentRepository.findById(subjectId);
    // Optional<Subject> subject = subjectRepository.findById(subjectId);

    // if (student.isPresent() && subject.isPresent()) {
    // Enrollment enrollment = new Enrollment();
    // enrollment.setStudent(student.get());
    // enrollment.setSubject(subject.get());
    // enrollmentRepository.save(enrollment);
    // return true;
    // } else {
    // return false;
    // }
    // }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByID(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentDetails.getName());
        student.setSubjects(studentDetails.getSubjects());
        student.setExams(studentDetails.getExams());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
