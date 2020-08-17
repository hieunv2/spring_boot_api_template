package com.artech.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends BaseRespone {
    private String access_token;

    public LoginResponse(int status,boolean success,String access_token ){
        this.status = status;
        this.success = success;
        this.access_token = access_token;
    }
}
