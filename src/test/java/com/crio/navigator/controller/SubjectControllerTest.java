package com.crio.navigator.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.crio.navigator.entity.Subject;
import com.crio.navigator.service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSubjects() throws Exception {
        Subject subject1 = new Subject(1L, "Math");
        Subject subject2 = new Subject(2L, "Science");

        when(subjectService.getAllSubjects()).thenReturn(Arrays.asList(subject1, subject2));

        mockMvc.perform(get("/api/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Math"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Science"));
    }

    @Test
    public void testGetSubjectById() throws Exception {
        Subject subject = new Subject(1L, "Math");

        when(subjectService.getSubjectById(1L)).thenReturn(Optional.of(subject));

        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void testGetSubjectByIdNotFound() throws Exception {
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateSubject() throws Exception {
        Subject subject = new Subject(1L, "Math");

        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);

        mockMvc.perform(post("/api/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void testUpdateSubject() throws Exception {
        Subject subject = new Subject(1L, "Math");

        when(subjectService.updateSubject(eq(1L), any(Subject.class))).thenReturn(subject);

        mockMvc.perform(put("/api/subjects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Math"));
    }

    @Test
    public void testDeleteSubject() throws Exception {
        mockMvc.perform(delete("/api/subjects/1"))
                .andExpect(status().isNoContent());
    }
}
