package it.corona.unitest.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessageDto {
    private Integer statusCode;
    private String message;
    private String description;
    private Date timestamp;
}
