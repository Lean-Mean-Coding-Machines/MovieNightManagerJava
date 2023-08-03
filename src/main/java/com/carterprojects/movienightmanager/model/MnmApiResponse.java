package com.carterprojects.movienightmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Getter
public class MnmApiResponse {
    Object data;
    Status status;

    MnmApiResponse(Object data, boolean success, HttpStatus code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static ResponseEntity<MnmApiResponse> success(Object data) {
        return  ResponseEntity.ok(new MnmApiResponse(data, true, HttpStatus.OK, ""));
    }

    public static ResponseEntity<MnmApiResponse> created(Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new MnmApiResponse(data, true, HttpStatus.CREATED, ""));
    }

    public static ResponseEntity<MnmApiResponse> failed(String message) {
        return ResponseEntity.badRequest().body(new MnmApiResponse(null, false, HttpStatus.BAD_REQUEST, message));
    }

    public static ResponseEntity<MnmApiResponse> failed(String message, HttpStatus code) {
        return ResponseEntity.status(code).body(new MnmApiResponse(null, false, code, message));
    }

    public static ResponseEntity<MnmApiResponse> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MnmApiResponse(null, false, HttpStatus.NOT_FOUND, "Data could not be found"));
    }

    @Getter
    public static class Status {
        boolean success;
        int code;
        String message;

        public Status(boolean success, HttpStatus code, String message) {
            this.success = success;
            this.code = code.value();
            this.message = message;
        }
    }
}
