package com.example.khaier.models;

public record ApiCustomResponse<T> (String message,
                                    T data,
                                    boolean isSuccessful){

}

