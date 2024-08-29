package com.wmt.smartparking.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wmtumanday
 */
@Data
public class BaseModel {

    /**
     * Current log user - add
     */
    private String createdBy;
    /**
     * Current log user - edit/delete
     */
    private String updatedBy;
    /**
     * Current log user - add
     */
    private LocalDateTime createdAt;
    /**
     * Current log user - edit/delete
     */
    private LocalDateTime updatedAt;

}
