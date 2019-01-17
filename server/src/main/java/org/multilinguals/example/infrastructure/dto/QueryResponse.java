package org.multilinguals.example.infrastructure.dto;

public class QueryResponse<T> extends AbstractResponse {
    private T data;

    public QueryResponse(T data) {
        this.data = data;
    }

    public QueryResponse(int code, T data) {
        super(code);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}