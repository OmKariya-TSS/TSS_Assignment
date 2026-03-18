package com.tss.hibernate_demo.dto.page;

import com.tss.hibernate_demo.dto.response.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponsePageDTO {

    private List<StudentResponseDTO> content;
    private int numberOfElements;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private int pageNumber;
    private int pageSize;
}
