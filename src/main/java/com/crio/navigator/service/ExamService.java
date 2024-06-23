package com.crio.navigator.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.navigator.dto.ExamDTO;
import com.crio.navigator.dto.StudentDTO;
import com.crio.navigator.dto.SubjectDTO;
import com.crio.navigator.entity.Exam;
import com.crio.navigator.entity.Subject;
import com.crio.navigator.exception.ExamNotFoundException;
import com.crio.navigator.repository.ExamRepository;
import com.crio.navigator.repository.SubjectRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<ExamDTO> getAllExam() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ExamDTO convertToDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(exam.getSubject().getId());
        subjectDTO.setName(exam.getSubject().getName());
        examDTO.setSubject(subjectDTO);

        List<StudentDTO> studentDTOs = exam.getStudents().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            return studentDTO;
        }).collect(Collectors.toList());
        examDTO.setStudents(studentDTOs);

        return examDTO;
    }

    public Exam convertToEntity(ExamDTO examDTO) {
        Exam exam = new Exam();
        exam.setId(examDTO.getId());
        Optional<Subject> subjectOpt = subjectRepository.findById(examDTO.getSubject().getId());
        System.out.println("Exam -> Subject Id is "+examDTO.getSubject().getId());
        if (!subjectOpt.isPresent()) {
            throw new IllegalArgumentException("Subject not found");
        }

        exam.setSubject(subjectOpt.get());

        return exam;
    }

    public Optional<Exam> getExamByID(Long id) {
        return examRepository.findById(id);
    }

    public Exam createExam(Exam exam) {
        System.out.println(exam.toString());
        return examRepository.save(exam);
    }

    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException());
        exam.setSubject(examDetails.getSubject());
        exam.setStudents(examDetails.getStudents());
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}
