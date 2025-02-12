package com.example.waiterservice.model.response;

import com.example.waiterservice.model.dto.ErrorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private String status;
    private HttpStatus code;
    private String dateTime;
    private List<ErrorDto> errors;
    private T results;
}
