package com.hyprful.firstmvvm.api.model;

public class ApiResponse<T> {
    T result;
    Throwable error;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
