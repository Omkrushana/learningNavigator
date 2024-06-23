package com.crio.navigator.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDTO {
    private Long id;
    private SubjectDTO subject;
    private List<StudentDTO> students;
    @Override
    public String toString() {
        return "ExamDTO [id=" + id + ", subject=" + subject + ", students=" + students + "]";
    }
    
}
