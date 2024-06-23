package com.crio.navigator.controller;

import com.crio.navigator.controller.ExamController;
import com.crio.navigator.dto.ExamDTO;
import com.crio.navigator.dto.SubjectDTO;
import com.crio.navigator.entity.Exam;
import com.crio.navigator.entity.Student;
import com.crio.navigator.entity.Subject;
import com.crio.navigator.service.ExamService;
import com.crio.navigator.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateExam() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");

        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject(subject);

        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(1L);
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Mathematics");
        examDTO.setSubject(subjectDTO);

        when(examService.convertToEntity(any(ExamDTO.class))).thenReturn(exam);
        when(examService.createExam(any(Exam.class))).thenReturn(exam);

        mockMvc.perform(post("/api/exams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(examDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterStudentForExam() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);

        Student student = new Student();
        student.setId(1L);

        when(examService.getExamByID(anyLong())).thenReturn(Optional.of(exam));
        when(studentService.getStudentByID(anyLong())).thenReturn(Optional.of(student));
        when(examService.updateExam(anyLong(), any(Exam.class))).thenReturn(exam);
        when(studentService.updateStudent(anyLong(), any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/exams/1/students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }   
}
