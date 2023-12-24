package com.example.khaier.model;

public record ApiCustomResponse<T> (String message,
                                    T data,
                                    boolean isSuccessful){

}

