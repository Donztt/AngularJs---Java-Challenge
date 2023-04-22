package com.example.BackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationRequestDTO {

    private String sortBy;
    private int pageNo;
    private int pageSize;
    public boolean descending;
}
