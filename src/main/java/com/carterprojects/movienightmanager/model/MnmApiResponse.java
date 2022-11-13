package com.carterprojects.movienightmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MnmApiResponse {
    Object data;
    Status status;

    MnmApiResponse(Object data, boolean success, int code, String message) {
        this.data = data;
        this.status = new Status(success, code, message);
    }

    public static MnmApiResponse success(Object data) {
        return new MnmApiResponse(data, true, 200, "");
    }

    public static MnmApiResponse created(Object data) {
        return new MnmApiResponse(data, true, 201, "");
    }

    public static MnmApiResponse failed(String message) {
        return new MnmApiResponse(null, false, 400, message);
    }

    public static MnmApiResponse notFound() {
        return new MnmApiResponse(null, false, 404, "Data could not be found");
    }

    @Getter
    @AllArgsConstructor
    static class Status {
        boolean success;
        int code;
        String message;
    }
}
