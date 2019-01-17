package org.multilinguals.example.infrastructure.dto;

public class CommandResponse<T> extends AbstractResponse {
    private T data;

    public CommandResponse(T data) {
        this.data = data;
    }

    public CommandResponse(int code, T data) {
        super(code);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}