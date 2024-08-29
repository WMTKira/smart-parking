package com.wmt.smartparking.dto;

import com.wmt.smartparking.validation.QueryGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseDto {

    /**
     * Current log user - add
     */
    private String createdBy;
    /**
     * Current log user - edit/delete
     */
    private String updatedBy;
    /**
     * Page number
     */
    @NotNull(message = "Page number cannot be empty!", groups = QueryGroup.class)
    private Integer pageNum;
    /**
     * Page size
     */
    @NotNull(message = "Page size cannot be empty!", groups = QueryGroup.class)
    private Integer pageSize;

}
