package com.artech.api.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = 5489598869007278721L;

    protected int status;
    protected String message;
    protected T data;
}