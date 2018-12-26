package org.multilinguals.example.infrastructure.dto;

public class Message<T> {
    private String name;

    private T data;

    public Message(T data) {
        this.name = data.getClass().getSimpleName();
        this.data = data;
    }

    public Message(String name, T data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public T getData() {
        return data;
    }
}
