package com.crio.navigator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crio.navigator.dto.ExamDTO;
import com.crio.navigator.entity.Exam;
import com.crio.navigator.entity.Student;
import com.crio.navigator.exception.ExamNotFoundException;
import com.crio.navigator.repository.StudentRepository;
import com.crio.navigator.repository.SubjectRepository;
import com.crio.navigator.service.ExamService;
import com.crio.navigator.service.StudentService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<ExamDTO> getAllExams() {
        return examService.getAllExam();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Optional<Exam> exam = examService.getExamByID(id);
        return exam.map(ResponseEntity::ok).orElseThrow(() -> new ExamNotFoundException());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        Exam updatedExam = examService.updateExam(id, examDetails);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{examId}/students/{studentId}")
    public ResponseEntity<?> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        Optional<Exam> examOpt = examService.getExamByID(examId);
        Optional<Student> studentOpt = studentService.getStudentByID(studentId);

        if (!examOpt.isPresent() || !studentOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Exam exam = examOpt.get();
        Student student = studentOpt.get();

        exam.addStudent(student);
        student.addExam(exam);

        examService.updateExam(examId, exam);
        studentService.updateStudent(studentId, student);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody ExamDTO examDTO) {
        System.out.println(examDTO.getId());
        Exam exam = examService.convertToEntity(examDTO);
        System.out.println(examDTO.toString());
        Exam createdExam = examService.createExam(exam);
        return ResponseEntity.ok(createdExam);
    }
}
