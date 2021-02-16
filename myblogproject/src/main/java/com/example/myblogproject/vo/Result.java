package com.example.myblogproject.vo;

public class Result<T> {
    private T value;
    private Boolean state;
    private String message;

    public Result() {
    }

    public Result(T value, Boolean state, String message) {
        this.value = value;
        this.state = state;
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Result<Object> getSuccessResult(){
        Result<Object> result = new Result<>();
        result.setState(true);
        result.setMessage("succeed");
        return  result;
    }
    public static Result<Object> getSuccessResult(String message){
        Result<Object> result = new Result<>();
        result.setState(true);
        result.setMessage(message);
        return  result;
    }

    public static Result<Object> getFailResult(){
        Result<Object> result = new Result<>();
        result.setState(false);
        result.setMessage("succeed");
        return  result;
    }
    public static Result<Object> getFailResult(String message){
        Result<Object> result = new Result<>();
        result.setState(false);
        result.setMessage(message);
        return  result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                ", state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}
