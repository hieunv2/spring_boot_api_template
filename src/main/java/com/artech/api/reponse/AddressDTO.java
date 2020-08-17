package com.artech.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private int addressId;

    private String title;

    private String phone;

    private Date createdAt;

    private Date updatedAt;

    private int status;

    private UserResponse users;
}
