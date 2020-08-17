package com.artech.api.reponse;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class BaseRespone {
    public int status;
    public boolean success;
}
