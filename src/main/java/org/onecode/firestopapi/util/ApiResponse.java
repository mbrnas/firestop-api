package org.onecode.firestopapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int errorCode;
    private long timestamp;
    private String path;
}
